package Servicos;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Adaptador.OperarNoBanco;
import App.App;
import Comandos.Comando;
import Comandos.ComandoPopMemento;
import Comandos.ComandoPushMemento;
import Entidades.Pessoa.Ator;
import Entidades.Pessoa.Diretor;
import Entidades.Pessoa.Pessoa;
import Entidades.Producao.Producao;
import Estados.bdEstado;
import Estados.bdEstadoDesconectado;

public final class Fachada {
    private Fachada(){}
    private static Fachada fachada = null;

    private App app = new App();
    private static Map<String, Comando> cmds = new HashMap<>() {{
        put("popMemento", new ComandoPopMemento());
        put("pushMemento", new ComandoPushMemento());
    }};

    private static bdEstado connBD = bdEstadoDesconectado.getInstancia();
    private OperarNoBanco operadorDoBanco = OperarNoBanco.getInstancia();
    
    
    public static Fachada getFachada() {
        if(fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    public void setLastStateApp() {
        App.setProducoes(cmds.get("popMemento").executar().getEstado());
        System.out.println("O app retornou para o estado anterior!");
    }

    private void addLastStateApp() {
        cmds.get("pushMemento").executar();
    }

    public void startConnection() throws SQLException {
        connBD = operadorDoBanco.operar(connBD);
    }

    public void finishConnection() throws SQLException {
        connBD = operadorDoBanco.operar(connBD);
    }

    public void addProducao(Producao p) {
        addLastStateApp();
        app.addProducao(p);
    }

    public void removerProducao(Integer id) {
        addLastStateApp();
        app.removerProducao(id);
    }

    public void executarProducao(Integer id) {
        app.getProducao(id).play();
    }

    public void listarProducoes() {
        app.listarProducoes();
    }

    public Producao getProducao(Integer id) {
        return app.getProducao(id);
    }

    public void addPessoa(Pessoa p, Integer idProducao) {
        addLastStateApp();
        app.getProducao(idProducao).addPessoa(p);
    }

    public void removerPessoa(Integer idPessoa, Integer idProducao) {
        addLastStateApp();
        app.getProducao(idProducao).removerPessoa(idProducao);
    }

    public Pessoa getPessoa(Integer id) {
        return app.getPessoa(id);
    }

    public void atualizarPessoa(Integer id, String membro, String novoDado) {
        Pessoa p = app.getPessoa(id);
        switch (membro) {
            case "id": p.setId(Integer.parseInt(novoDado)); break;
            case "nome": p.setNome(novoDado); break;
            case "idade": p.setIdade(Integer.parseInt(novoDado)); break;
            case "linkBiografia": p.setLinkBiografia(novoDado); break;
            case "nmrDeProducoes": Diretor d = (Diretor) p; d.setNmrDeProducoes(Integer.parseInt(novoDado)); break;
            case "tempoDeCarreira": Ator a = (Ator) p; a.setTempoDeCarreira(Integer.parseInt(novoDado)); break;
            default: System.out.println("Operação falhou! Membro não encontrado.");
        }
    }
}
