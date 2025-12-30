package lei.grupo4.java;

public class Testes {
    public static void main(String[] args){

        // Cria o MenuItem
        MenuItem hamburger = new MenuItem(1, "Hambúrguer Clássico", 8.5f, true, null);

        // Verifica se pode ser preparado
        if (hamburger.podeSerPreparado()) {
            System.out.println("Hambúrguer pode ser preparado");

            // Remove stock
            hamburger.consumirStock();
            System.out.println("Stock atualizado após pedido");
        } else {
            System.out.println("Hambúrguer NÃO pode ser preparado");
        }
    }
}
