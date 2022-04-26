public class PessoaFisica extends Cliente {
    private String cpf;

    PessoaFisica(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", CPF: " + this.cpf;
    }

}
