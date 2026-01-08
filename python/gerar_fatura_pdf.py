import sys
from pathlib import Path
from reportlab.pdfgen import canvas
# Importamos o consumidor
from Fatura_Consumer import FaturaConsumer


def gerar_pdf(id_procurado):
    # 1. Obter dados através do Consumer
    consumer = FaturaConsumer()
    lista_faturas = consumer.data

    # 2. Encontrar a fatura específica pelo ID
    fatura_obj = next((f for f in lista_faturas if str(f._id) == str(id_procurado)), None)

    if not fatura_obj:
        print(f"Fatura {id_procurado} não encontrada nos objetos consumidos.")
        return

    # 3. Configurar caminhos de output

    pdf_path = f"output/Talao_{id_procurado}.pdf"

    # 4. Desenhar o PDF
    c = canvas.Canvas(str(pdf_path))
    y = 800

    c.setFont("Helvetica-Bold", 14)
    c.drawString(50, y, "RESTAURANTE GRUPO 4")
    y -= 30

    c.setFont("Helvetica", 12)
    c.drawString(50, y, f"Fatura ID: {fatura_obj._id}")
    y -= 20
    c.drawString(50, y, f"Pedido: {fatura_obj._pedido}")
    y -= 20
    c.drawString(50, y, f"Método de Pagamento: {fatura_obj._metodo_pagamento}")
    y -= 30

    c.drawString(50, y, "Items:")
    y -= 20
    # O atributo _items é uma lista de dicts: [{"Pizza": 12.0}, {"Agua": 1.5}]
    for item_dict in fatura_obj._items:
        for nome, preco in item_dict.items():
            c.drawString(60, y, f"{nome} - {preco} EUR")
            y -= 20

    y -= 10
    c.setFont("Helvetica-Bold", 12)
    c.drawString(50, y, f"TOTAL: {fatura_obj._preco} EUR")

    y -= 40
    c.setFont("Helvetica-Oblique", 10)
    c.drawString(50, y, "Obrigado pela preferência! :)")

    c.save()
    print(f"PDF gerado com sucesso em: {pdf_path}")


if __name__ == "__main__":
    id_input = sys.argv[1] if len(sys.argv) > 1 else "1"
    gerar_pdf(id_input)