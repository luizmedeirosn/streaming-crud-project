package Memento;

import java.util.HashSet;
import java.util.Set;

import App.App;
import Entidades.Producao.Producao;

public class Memento {
    private Set<Producao> estado;
    
    public Memento() {
        estado = new HashSet<>();
        for (Producao prod : App.getProducoes()) {
            estado.add(prod);
        }
    }
    
    public Set<Producao> getEstado() {
        return estado;
    }
}
