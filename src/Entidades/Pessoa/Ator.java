package Entidades.Pessoa;

public final class Ator extends Pessoa {
    private Integer tempoDeCarreira;
    private EnumTempoDeCarreira etdc;   // Unidade de tempo para o tempo carreira, posso usar State.
    
    public Ator(Integer id, String nome, Integer idade, Integer tempoDeCarreira, EnumTempoDeCarreira etdc, String linkBiografia) {
        super(id, nome, idade, linkBiografia);
        this.tempoDeCarreira = tempoDeCarreira;
        this.etdc = etdc;
    }

    @Override
    public String toString() {
        String[] dataHora = super.getHoraDoCadastro().split(" ");
        return String.format("-------------------------------------------------------%nAtor: %s%n   ID: %d%n   Idade: %d%n   Tempo de Carreira: %d %s.%n   Data-Hora do Cadastro no Sistema: %s às %s horas.%n   Link para a Biografia: %s%n-------------------------------------------------------%n", super.getNome(), super.getId(), super.getIdade(), tempoDeCarreira, etdc, dataHora[0], dataHora[1], super.getLinkBiografia());
    }

    public Integer getTempoDeCarreira() {
        return tempoDeCarreira;
    }
    
    public void setTempoDeCarreira(Integer tempoDeCarreira) {
        this.tempoDeCarreira = tempoDeCarreira;
    }

    public EnumTempoDeCarreira getUt() {
        return etdc;
    }
}