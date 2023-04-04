package Entidades.Producao;

public final class Filme extends Producao {
    private String duracao;

    public Filme(Integer id, String nome, String linkProducao, String duracao, String distribuicao, String estreia) {
        super(id, nome, linkProducao, distribuicao, estreia);
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Filme: %s%n   Estreia: %s%n   Distribuição: %s%n   Link:  %s%n   Duração: %s%nData-Hora do Cadastro no Sistema: %s.%n%nElenco de Atores e Diretores:%n", super.getNome(), super.getEstreia(), super.getDistribuicao(), super.getLinkProducao(), duracao, super.getHoraDoCadastro()));
        for(Object obj : pessoas) {
            s.append(obj.toString());
        }
        s.append('\n');
        return s.toString();
    }

    @Override
    public void play() {
        System.out.println(this);
    }

    @Override
    public String getInfo() {
        return String.format("Filme: %s%n   ID:   %d%n   Estreia: %s%n   Distribuição: %s%n   Link:  %s%n   Duração: %s%nData-Hora do Cadastro no Sistema: %s.%n%n", super.getNome(), super.getId(), super.getEstreia(), super.getDistribuicao(), super.getLinkProducao(), duracao, super.getHoraDoCadastro());
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
