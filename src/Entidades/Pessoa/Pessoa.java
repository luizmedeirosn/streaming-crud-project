package Entidades.Pessoa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa implements Comparable<Pessoa> {
    private String nome, linkBiografia;
    private Integer id, idade;
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime horaDoCadastro = LocalDateTime.now();

    public abstract String toString();

    protected Pessoa(Integer id, String nome, Integer idade, String linkBiografia) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.linkBiografia = linkBiografia;
    }

    @Override
    public int compareTo(Pessoa pessoa) {
        return this.id.compareTo(pessoa.getId());
    }

    public String getLinkBiografia() {
        return linkBiografia;
    }

    public void setLinkBiografia(String linkBiografia) {
        this.linkBiografia = linkBiografia;
    }

    public String getHoraDoCadastro() {
        return horaDoCadastro.format(fmt);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Pessoa other = (Pessoa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
