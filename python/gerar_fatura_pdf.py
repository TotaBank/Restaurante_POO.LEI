import json
from reportlab.pdfgen import canvas
from pathlib import Path
import sys

# Caminho do JSON
BASE_DIR = Path(__file__).resolve().parents[2]
FATURAS_JSON = BASE_DIR / "src/main/java/lei/grupo4/resources/Faturas.json"
OUTPUT_DIR = BASE_DIR / "scripts/python/output"
OUTPUT_DIR.mkdir(parents=True, exist_ok=True)

# Ler fatura
id_fatura = sys.argv[1] if len(sys.argv) > 1 else "1"
dados_raw = json.load(open(FATURAS_JSON, encoding="utf-8"))
fatura = dados_raw.get(id_fatura)

if not fatura:
    print(f"Fatura {id_fatura} não encontrada")
    sys.exit(1)

# Criar PDF
pdf_path = OUTPUT_DIR / f"Talao_{id_fatura}.pdf"
c = canvas.Canvas(str(pdf_path))
y = 800

# Cabeçalho simples
c.setFont("Helvetica-Bold", 14)
c.drawString(50, y, "RESTAURANTE GRUPO 4")
y -= 30

# Info fatura
c.setFont("Helvetica", 12)
c.drawString(50, y, f"Fatura: {id_fatura}")
y -= 20
c.drawString(50, y, f"Pedido: {fatura['Pedido']}")
y -= 20
c.drawString(50, y, f"Método de Pagamento: {fatura['Método de Pagamento']}")
y -= 30

# Itens
c.drawString(50, y, "Items:")
y -= 20
for item in fatura["Items"]:
    for nome, preco in item.items():
        c.drawString(60, y, f"{nome} - {preco} EUR")
        y -= 20

# Total
y -= 10
c.drawString(50, y, f"TOTAL: {fatura['Preco']} EUR")

# Rodapé
y -= 40
c.drawString(50, y, "Obrigado! :)")

c.save()
print(f"PDF gerado em {pdf_path}")
