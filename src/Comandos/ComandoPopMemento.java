package Comandos;

import App.App;
import Memento.Memento;

public class ComandoPopMemento implements Comando {
    @Override
    public Memento executar() {
        if (!App.getAppMementos().empty()) return App.getAppMementos().pop();
        System.out.println("Stack de mementos vazia!");
        return null;
    }
}
