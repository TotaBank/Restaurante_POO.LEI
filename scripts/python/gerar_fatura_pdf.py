import json
import sys
from datetime import datetime
from reportlab.pdfgen import canvas
from reportlab.lib.units import mm
from pathlib import Path

# ================= CONFIGURAÇÕES =================
BASE_DIR = Path(__file__).resolve().parents[2]  # raiz do projeto
FATURAS_JSON = BASE_DIR / "src/main/java/lei/grupo4/resources/Faturas.json"
OUTPUT_DIR = BASE_DIR / "scripts/python/output"

LARGURA_PAPEL = 80 * mm
MARGEM = 4 * mm

# ================= FUNÇÕES =================
def desenhar_linha(c, y_pos):
    c.setDash(1, 2)  # tracejado
    c.setLineWidth(0.5)
    c.line(MARGEM, y_pos, LARGURA_PAPEL - MARGEM, y_pos)
    c.setDash([])  # reset
    return y_pos - 4 * mm

def escrever_centrado(c, texto, y_pos, tamanho=9, bold=False):
    fonte = "Helvetica-Bold" if bold else "Helvetica"
    c.setFont(fonte, tamanho)
    c.drawCentredString(LARGURA_PAPEL / 2, y_pos, texto)
    return y_pos - (tamanho / 2.8 * mm) - 2 * mm

def gerar_talao(id_fatura):
    # 1️⃣ Ler JSON
    try:
        with open(FATURAS_JSON, "r", encoding="utf-8") as f:
            dados_raw = json.load(f)
    except Exception as e:
        print(f"Erro ao ler JSON: {e}")
        return

    dados = dados_raw.get(str(id_fatura))
    if not dados:
        print(f"Fatura {id_fatura} não encontrada. IDs disponíveis:", list(dados_raw.keys()))
        return

    lista_items = dados.get("Items", [])
    ALTURA_PAPEL = (90 + len(lista_items) * 10) * mm
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
    nome_pdf = OUTPUT_DIR / f"Talao_{id_fatura}.pdf"

    c = canvas.Canvas(str(nome_pdf), pagesize=(LARGURA_PAPEL, ALTURA_PAPEL))
    y = ALTURA_PAPEL - 10 * mm

    # --- CABEÇALHO ---
    y = escrever_centrado(c, "RESTAURANTE GRUPO 4", y, 11, True)
    y = escrever_centrado(c, "ESGTS - Santarém", y, 8)
    y = escrever_centrado(c, "NIF: 500 123 456", y, 8)
    y -= 2 * mm
    y = desenhar_linha(c, y)

    # --- INFO FATURA ---
    c.setFont("Helvetica", 8)
    c.drawString(MARGEM, y, f"Fatura: {id_fatura}")
    data_hoje = datetime.now().strftime("%d/%m/%Y %H:%M")
    c.drawRightString(LARGURA_PAPEL - MARGEM, y, data_hoje)
    y -= 4 * mm
    c.drawString(MARGEM, y, f"Pedido Ref: {dados.get('Pedido', '-')}")
    y -= 4 * mm
    y = desenhar_linha(c, y)

    # --- ITENS ---
    c.setFont("Helvetica-Bold", 8)
    c.drawString(MARGEM, y, "ARTIGO")
    c.drawRightString(LARGURA_PAPEL - MARGEM, y, "TOTAL")
    y -= 4 * mm

    c.setFont("Helvetica", 9)
    for item_dict in lista_items:
        for nome, preco in item_dict.items():
            nome_display = nome[:22] + ".." if len(nome) > 24 else nome
            c.drawString(MARGEM, y, nome_display)
            c.drawRightString(LARGURA_PAPEL - MARGEM, y, f"{float(preco):.2f}")
            y -= 6 * mm

    y += 2 * mm
    y = desenhar_linha(c, y)

    # --- TOTAIS ---
    total = dados.get("Preco", 0)
    metodo = dados.get("Método de Pagamento", "Numerário")

    c.setFont("Helvetica-Bold", 12)
    c.drawString(MARGEM, y, "TOTAL")
    c.drawRightString(LARGURA_PAPEL - MARGEM, y, f"{float(total):.2f} EUR")
    y -= 8 * mm

    c.setFont("Helvetica", 8)
    c.drawString(MARGEM, y, "Pagamento:")
    c.drawRightString(LARGURA_PAPEL - MARGEM, y, metodo)
    y -= 6 * mm

    y = desenhar_linha(c, y)

    # --- RODAPÉ ---
    y = escrever_centrado(c, "Obrigado pela visita!", y, 9, True)
    y = escrever_centrado(c, "*** Processado por Computador ***", y, 7)

    c.save()
    print(f"Sucesso: Talão gerado em '{nome_pdf}'")

# ================= PONTO DE ENTRADA =================
if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Uso: python gerar_talao.py <id_fatura>")
        sys.exit(1)

    id_fatura = sys.argv[1]
    gerar_talao(id_fatura)
