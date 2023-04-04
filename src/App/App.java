package App;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import Entidades.Pessoa.Pessoa;
import Entidades.Producao.Producao;
import Memento.Memento;

public final class App {
    private static final String nome = "STREAMING";
    private static Stack<Memento> appMementos = new Stack<>();
    private static Set<Producao> producoes = new HashSet<>();

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Producao prod: producoes) s.append(prod.toString());
        return s.toString();
    }

    public static Stack<Memento> getAppMementos() {
        return appMementos;
    }

    public static Set<Producao> getProducoes() {
        return producoes;
    }

    public static void setProducoes(Set<Producao> producoes) {
        App.producoes = producoes;
    }
    
    public static String getNome() {
        return nome;
    }

    public App addProducao(Producao producao) {
        producoes.add(producao);
        return this;
    }

    public App removerProducao(Integer id) {
        producoes.remove(this.getProducao(id));
        return this;
    }

    public Producao getProducao(Integer id) {
        for (Producao producao : producoes) if(producao.getId() == id) return producao;
        return null;
    }

    public void listarProducoes() {
        System.out.println("Séries e Filmes Cadastrados:\n");
        for (Producao producao : producoes) System.out.println(producao.getInfo()); 
    }

    public Pessoa getPessoa(Integer id) {
        for (Producao prod : producoes){
            Pessoa p = prod.getPessoa(id);
            if (p != null && p.getId() == id) {
                return prod.getPessoa(id);
            }
        }
        System.out.println("Pessoa não encontrada nas produções!");
        return null;
    }
}