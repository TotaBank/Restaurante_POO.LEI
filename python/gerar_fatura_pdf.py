
import sys
from datetime import datetime
from reportlab.pdfgen import canvas
from reportlab.lib.units import mm
from Fatura_Consumer import FaturaConsumer


def gerar_pdf(id_procurado):
    # 1. Obter dados através do Consumer
    consumer = FaturaConsumer()
    lista_faturas = consumer.data

    fatura = next((f for f in lista_faturas if str(f._id) == str(id_procurado)), None)

    if not fatura:
        print(f"Fatura {id_procurado} não encontrada.")
        return

    # 2. Configuração do PDF (Talão térmico 80mm)
    LARGURA_PAPEL = 80 * mm
    MARGEM = 4 * mm

    num_items = sum(len(i) for i in fatura._items)
    ALTURA_PAPEL = (90 + (num_items * 10)) * mm

    pdf_path = f"output/Talao_{id_procurado}.pdf"
    c = canvas.Canvas(pdf_path, pagesize=(LARGURA_PAPEL, ALTURA_PAPEL))

    y = ALTURA_PAPEL - 10 * mm

    # ---------- FUNÇÕES AUXILIARES ----------
    def linha_tracejada(y_pos):
        c.setDash(1, 2)
        c.setLineWidth(0.5)
        c.line(MARGEM, y_pos, LARGURA_PAPEL - MARGEM, y_pos)
        c.setDash([])
        return y_pos - 4 * mm

    def texto_centrado(texto, y_pos, size=9, bold=False):
        fonte = "Helvetica-Bold" if bold else "Helvetica"
        c.setFont(fonte, size)
        c.drawCentredString(LARGURA_PAPEL / 2, y_pos, texto)
        return y_pos - (size / 2.8 * mm) - 2 * mm

    # ---------- CABEÇALHO ----------
    y = texto_centrado("RESTAURANTE GRUPO 4", y, 11, True)
    y = texto_centrado("ESGTS - Santarém", y, 8)
    y = texto_centrado("NIF: 500 123 456", y, 8)

    y -= 2 * mm
    y = linha_tracejada(y)

    # ---------- INFO FATURA ----------
    c.setFont("Helvetica", 8)
    c.drawString(MARGEM, y, f"Fatura: {fatura._id}")

    data = datetime.now().strftime("%d/%m/%Y %H:%M")
    c.drawRightString(LARGURA_PAPEL - MARGEM, y, data)

    y -= 4 * mm
    c.drawString(MARGEM, y, f"Pedido Ref: {fatura._pedido}")
    y -= 4 * mm

    y = linha_tracejada(y)

    # ---------- ITENS ----------
    c.setFont("Helvetica-Bold", 8)
    c.drawString(MARGEM, y, "ARTIGO")
    c.drawRightString(LARGURA_PAPEL - MARGEM, y, "TOTAL")
    y -= 4 * mm

    c.setFont("Helvetica", 9)
    for item_dict in fatura._items:
        for nome, preco in item_dict.items():
            nome_display = nome[:22] + ".." if len(nome) > 24 else nome
            c.drawString(MARGEM, y, nome_display)
            c.drawRightString(LARGURA_PAPEL - MARGEM, y, f"{float(preco):.2f}")
            y -= 6 * mm

    y += 2 * mm
    y = linha_tracejada(y)

    # ---------- TOTAIS ----------
    c.setFont("Helvetica-Bold", 12)
    c.drawString(MARGEM, y, "TOTAL")
    c.drawRightString(
        LARGURA_PAPEL - MARGEM, y, f"{float(fatura._preco):.2f} EUR"
    )
    y -= 8 * mm

    c.setFont("Helvetica", 8)
    c.drawString(MARGEM, y, "Pagamento:")
    c.drawRightString(LARGURA_PAPEL - MARGEM, y, fatura._metodo_pagamento)
    y -= 6 * mm

    y = linha_tracejada(y)

    # ---------- RODAPÉ ----------
    y = texto_centrado("Obrigado pela preferência!", y, 9, True)
    y = texto_centrado("*** Processado por Computador ***", y, 7)

    c.save()
    print(f"PDF gerado com sucesso: {pdf_path}")


if __name__ == "__main__":
    id_input = sys.argv[1] if len(sys.argv) > 1 else "1"
    gerar_pdf(id_input)
