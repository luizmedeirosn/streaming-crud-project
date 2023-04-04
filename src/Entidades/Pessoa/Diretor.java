package Entidades.Pessoa;

public final class Diretor extends Pessoa {
    private Integer nmrDeProducoes;

    public Diretor(Integer id, String nome, Integer idade, Integer nmrDeProducoes, String linkBiografia) {
        super(id, nome, idade, linkBiografia);
        this.nmrDeProducoes = nmrDeProducoes;
    }

    @Override
    public String toString() {
        String[] dataHora = super.getHoraDoCadastro().split(" ");
        return String.format("-------------------------------------------------------%nDiretor: %s%n   ID: %d%n   Idade: %d%n   Número de Produções: %d.%n   Data-Hora do Cadastro no Sistema: %s às %s horas.%n   Link para a Biografia: %s%n-------------------------------------------------------%n", super.getNome(), super.getId(), super.getIdade(), nmrDeProducoes, dataHora[0], dataHora[1], super.getLinkBiografia());
    }

    public Integer getNmrDeProducoes() {
        return nmrDeProducoes;
    }

    public void setNmrDeProducoes(Integer nmrDeProducoes) {
        this.nmrDeProducoes = nmrDeProducoes;
    }
}