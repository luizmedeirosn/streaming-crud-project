package Entidades.Producao;

import java.util.Set;
import java.util.TreeSet;

public final class Serie extends Producao {
    private Integer nmrDeTemporadas;
    private Set<Temporada> temporadas; // Nunca inicializar variáveis na definção pq na hora do casting o compilador irá utilizar o nmr da hora da definição.

    public Set<Temporada> getTemporadas() {
        return temporadas;
    }

    public Serie(Integer id, String nome, String linkProducao, String distribuicao, String estreia) {
        super(id, nome, linkProducao, distribuicao, estreia);
        nmrDeTemporadas = 0;
        temporadas = new TreeSet<>();
    }

    @Override
    public void play() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Série: %s%n   Estreia: %s%n   Distribuição: %s%n   Link:  %s%n   Número de Temporadas: %d%n", super.getNome(), super.getEstreia(), super.getDistribuicao(), super.getLinkProducao(), nmrDeTemporadas));
        for(Object obj: temporadas) s.append("      " + obj.toString());
        s.append(String.format("Data-Hora do Cadastro no Sistema: %s.%n%nElenco de Atores e Diretores:%n", super.getHoraDoCadastro()));
        for(Object obj : pessoas) s.append(obj.toString());
        s.append('\n');
        return s.toString();
    }

    @Override
    public String getInfo() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Série: %s%n   ID:   %d%n   Estreia: %s%n   Distribuição: %s%n   Link:  %s%n   Número de Temporadas: %d%n", super.getNome(), super.getId(), super.getEstreia(), super.getDistribuicao(), super.getLinkProducao(), nmrDeTemporadas));
        for(Object obj: temporadas) s.append("      " + obj.toString());
        s.append(String.format("Data-Hora do Cadastro no Sistema: %s.%n%n", super.getHoraDoCadastro()));
        return s.toString();
    }

    @Override
    public void addTemporada(Temporada temporada) {
        temporadas.add(temporada);
        nmrDeTemporadas += 1;
    }

    @Override
    public void removerTemporada(Temporada temporada) {
        temporadas.remove(temporada);
        nmrDeTemporadas -= 1;
    }

    public Integer getNmrDeTemporadas() {
        return nmrDeTemporadas;
    }

    public void setNmrDeTemporadas(Integer nmrDeTemporadas) {
        this.nmrDeTemporadas = nmrDeTemporadas;
    }
}
