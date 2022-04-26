import java.util.ArrayList;
import java.util.Scanner;

public final class Main {
    private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static ArrayList<Reserva> listaEspera = new ArrayList<Reserva>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("(Digite 0 Para Ver Opcoes)");
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
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" Restaurante Sabor Sofisticado");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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

        TipoPessoa tp_c = escolherCliente();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        switch (tp_c) {
            case FISICA:
                System.out.print("Insira o cpf: ");
                String cpf = scanner.nextLine();
                if (!codigoJaExiste()){
                    PessoaFisica pf = new PessoaFisica(nome, cpf);
                    novo_cliente = pf;
                } else {
                    System.out.println("Cpf ja Cadastrado!");
                }
                break;
            case JURIDICA:
                System.out.print("Insira o cnpj: ");
                String cnpj = scanner.nextLine();
                if (!codigoJaExiste()) {
                    PessoaJuridica pj = new PessoaJuridica(nome, cnpj);
                    novo_cliente = pj;
                }
                else {
                    System.out.println("Cnpj ja Cadastrado!");
                }
                break;
        }

        TipoPagamento tp_p = escolherPagamento();
        boolean pagamentoAVista;
        switch (tp_p) {
            case AVISTA:
                pagamentoAVista = true;
                break;
            case PARCELADO:
                pagamentoAVista = false;
                break;
            default:
                pagamentoAVista = false;
        }

        Reserva nova_reserva = new Reserva(novo_cliente, pagamentoAVista);
        if( reservas.size() < 7) {
            reservas.add(nova_reserva);
        } else {
            listaEspera.add(nova_reserva);
        }

    }
    
    private static Reserva pesquisar_reserva(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o CPF ou CNPJ: ");
        String dado = scanner.nextLine();

        Reserva reservaEncontrada = null;
        for (Reserva reserva : reservas) {
            if (reserva.cliente instanceof PessoaFisica) {
                PessoaFisica cliente = (PessoaFisica) reserva.cliente;
                if (cliente.getCpf().equals(dado)){
                    reservaEncontrada = reserva;
                }
            } else {
                PessoaJuridica cliente = (PessoaJuridica) reserva.cliente;
                if (cliente.getCnpj().equals(dado)){
                    reservaEncontrada = reserva;
                }
            }
        }

        if (reservaEncontrada.equals(null)){
            System.out.println(">> Cliente Não Possui Reserva! <<");
        } else {
            System.out.println(">> Cliente Possui Reserva <<");
        }

        return reservaEncontrada;
    }
    
    private static boolean codigoJaExiste(){
        if (pesquisar_reserva().equals(null)) {
            return false;
        }
        return true;
    }

    private static void imprimir_reservas(){
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private static void imprimir_espera(){
        for (Reserva reserva : listaEspera) {
            System.out.println(reserva);
        }
    }   

    private static void cancelar_reserva(){
        Reserva reserva = pesquisar_reserva();
        reservas.remove(reserva);
        System.out.println(">> Reserva Cancelada <<");
    }

    private static TipoPessoa escolherCliente(){
        Scanner scanner = new Scanner(System.in);
        String tp = "";
        while (!tp.equals("j") && !tp.equals("f")) {
            System.out.print("Tipo do Cliente? [F|J] ");
            tp = scanner.nextLine().toLowerCase();    
            if (!tp.equals("j") && !tp.equals("f")) {
                System.err.println("F: Física | J: Jurídica");
            }
        }
        return tp.equals("f") ? TipoPessoa.FISICA : TipoPessoa.JURIDICA;
    }

    private static TipoPagamento escolherPagamento(){
        Scanner scanner = new Scanner(System.in);
        String tp = "";
        while (!tp.equals("a") && !tp.equals("p")) {
            System.out.print("Tipo do Pagamento? [A|P] ");
            tp = scanner.nextLine().toLowerCase();    
            if (!tp.equals("a") && !tp.equals("p")) {
                System.err.println("A: A Vista | P: Parcelado");
            }
        }
        return tp.equals("a") ? TipoPagamento.AVISTA : TipoPagamento.PARCELADO;
    }
}
