package Entidades.Producao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import Entidades.Pessoa.Pessoa;

public abstract class Producao {
    private Integer id = null;
    private String nome, linkProducao, distribuicao;
    private static DateTimeFormatter ftm1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter ftm2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDate estreia;
    private LocalDateTime horaDoCadastro = LocalDateTime.now();
    protected Set<Pessoa> pessoas = new TreeSet<>();

    public Producao(Integer id, String nome, String linkProducao, String distribuicao, String estreia) {
        this.id = id;
        this.nome = nome;
        this.linkProducao = linkProducao;
        this.distribuicao = distribuicao;
        this.estreia = LocalDate.parse(estreia, ftm1);
    }

    public abstract void play();
    public abstract String getInfo();
    public void addTemporada(Temporada temporada){};
    public void removerTemporada(Temporada temporada){};


    public Pessoa getPessoa(Integer id) {
        for(Pessoa p : pessoas) {
            int pid = p.getId();
            if( pid == id) return p;
        } 
        return null;
    }
    
    public Producao addPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
        return this;
    }

    public Producao removerPessoa(Integer id) {
        pessoas.remove(this.getPessoa(id));
        return this;
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLinkProducao() {
        return linkProducao;
    }
    
    public void setLinkProducao(String linkProducao) {
        this.linkProducao = linkProducao;
    }

    public String getDistribuicao() {
        return distribuicao;
    }

    public void setDistribuicao(String distribuicao) {
        this.distribuicao = distribuicao;
    }

    public String getEstreia() {
        return estreia.format(ftm1);
    }

    public void setEstreia(LocalDate estreia) {
        this.estreia = estreia;
    }

    public String getHoraDoCadastro() {
        return horaDoCadastro.format(ftm2);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        Producao other = (Producao) obj;
        if (id == null && other.id != null) return false;
        else if (!id.equals(other.id)) return false;
        return true;
    }

   
}
