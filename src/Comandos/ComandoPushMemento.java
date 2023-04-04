package Comandos;

import App.App;
import Memento.Memento;

public class ComandoPushMemento implements Comando{
    @Override
    public Memento executar() {
        Memento m = new Memento();
        App.getAppMementos().add(m);
        return m;
    }
}
