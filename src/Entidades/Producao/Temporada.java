package Entidades.Producao;


public final class Temporada implements Comparable<Temporada>{
    private Integer nmrDaTemporada;
    private String linkDaTemporada;
    
    public Temporada(Integer nmrDaTemporada, String linkDaTemporada) {
        this.nmrDaTemporada = nmrDaTemporada;
        this.linkDaTemporada = linkDaTemporada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nmrDaTemporada == null) ? 0 : nmrDaTemporada.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Temporada other = (Temporada) obj;
        if (nmrDaTemporada == null) {
            if (other.nmrDaTemporada != null)
                return false;
        } else if (!nmrDaTemporada.equals(other.nmrDaTemporada))
            return false;
        return true;
    }

    @Override
    public int compareTo(Temporada temporada) {
        return this.nmrDaTemporada.compareTo(temporada.getNmrDaTemporada());
    }

    @Override
    public String toString() {
        return String.format("Temporada %d -> Link: %s%n", nmrDaTemporada, linkDaTemporada);
    }

    public Integer getNmrDaTemporada() {
        return nmrDaTemporada;
    }

    public void setNmrDaTemporada(Integer nmrDaTemporada) {
        this.nmrDaTemporada = nmrDaTemporada;
    }

    public String getLinkDaTemporada() {
        return linkDaTemporada;
    }

    public void setLinkDaTemporada(String linkDaTemporada) {
        this.linkDaTemporada = linkDaTemporada;
    }
}
