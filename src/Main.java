import java.util.ArrayList;
import java.util.Scanner;

public final class Main {
    private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static ArrayList<Reserva> listaEspera = new ArrayList<Reserva>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            try {
                System.out.print("User> ");
                String input = scanner.nextLine().trim().toLowerCase();
        
                if ("".equals(input)) {
                } else if ("0".equals(input)) {
                    help();
                } else if ("1".equals(input)) {
                    reservar_mesa();
                } else if ("2".equals(input)) {
                    pesquisar_reserva();
                } else if ("3".equals(input)) {
                    imprimir_reservas();
                } else if ("4".equals(input)) {
                    imprimir_espera();
                } else if ("5".equals(input)) {
                    cancelar_reserva();
                } else if ("6".equals(input)) {
                    exit = true;
                }else {
                    System.err.println("Opcao Invalida. Digite \"0\" para ajuda");
                    throw new UnsupportedOperationException();
                }
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
        }
    }

    private static void help() {
        System.out.println("Restaurante ~Sabor Sofisticado~");
        System.out.println("_______________________________");
        System.out.println();
        System.out.println("0. Menu de Opcoes");
        System.out.println("1. Reservar Mesa");
        System.out.println("2. Pesquisar Reserva");
        System.out.println("3. Imprimir Reservas");
        System.out.println("4. Imprimir Lista de Espera");
        System.out.println("5. Cancelar Reserva");
        System.out.println();
        System.out.println("6. Finalizar");
    }

    private static void reservar_mesa(){
        Scanner scanner = new Scanner(System.in);
        Cliente novo_cliente = null;

        System.out.print("Voce deseja adicionar um cliente \"1\" ou empresa \"2\": ");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        switch (tipoCliente) {
            case 1:
                System.out.println("Insira o cpf: ");
                String cpf = scanner.nextLine();
                PessoaFisica pf = new PessoaFisica(nome, cpf);
                novo_cliente = pf;
                break;
            case 2:
                System.out.println("Insira o cnpj: ");
                String cnpj = scanner.nextLine();
                PessoaJuridica pj = new PessoaJuridica(nome, cnpj);
                novo_cliente = pj;
                break;
        }

        System.out.println("Tipo de Pagamento");
        System.out.print("\"1\" A Vista / \"2\" Parcelado: ");
        String tipoPagamento = scanner.nextLine();

        boolean pagamentoAVista;
        if (tipoPagamento.trim().equals("1")){
            pagamentoAVista = true;
        } else {
            pagamentoAVista = false;
        }

        Reserva nova_reserva = new Reserva(novo_cliente, pagamentoAVista);
        if( reservas.size() < 7) {
            reservas.add(nova_reserva);
        } else {
            listaEspera.add(nova_reserva);
        }

    }

    private static void pesquisar_reserva(){

    }
    
    private static void imprimir_reservas(){
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private static void imprimir_espera(){

    }   

    private static void cancelar_reserva(){

    }
}