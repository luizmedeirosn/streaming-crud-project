package Estados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import DataBase.DataBase;
import Entidades.Pessoa.Ator;
import Entidades.Pessoa.Diretor;
import Entidades.Pessoa.EnumTempoDeCarreira;
import Entidades.Pessoa.Pessoa;
import Entidades.Producao.Filme;
import Entidades.Producao.Producao;
import Entidades.Producao.Serie;
import Entidades.Producao.Temporada;
import Servicos.Fachada;

public final class bdEstadoDesconectado implements bdEstado {
    private bdEstadoDesconectado() {}
    private static bdEstadoDesconectado instancia = null;
    private Fachada f = Fachada.getFachada();

    public static bdEstadoDesconectado getInstancia() {
        if(instancia == null) {
            instancia = new bdEstadoDesconectado();
        }
        return instancia;
    }

    @Override
    public bdEstado processar() throws SQLException {
        Connection connBD = DataBase.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        statement = connBD.createStatement();

        resultSet = statement.executeQuery("select *from filme");
        while(resultSet.next()) {
            Producao p = new Filme(resultSet.getInt("id"), 
            resultSet.getString("nome"), 
            resultSet.getString("linkProducao"), 
            resultSet.getString("duracao"), 
            resultSet.getString("distribuicao"), 
            resultSet.getString("estreia"));
            f.addProducao(p);
        }

        resultSet = statement.executeQuery("select *from serie");
        while(resultSet.next()) {
            Producao p = new Serie(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("linkProducao"), resultSet.getString("distribuicao"), resultSet.getString("estreia"));
            f.addProducao(p);
        }

        resultSet = statement.executeQuery("select *from temporada");
        while(resultSet.next()) {
            Temporada t = new Temporada(resultSet.getInt("id"), resultSet.getString("linkTemporada"));
            Producao prod = f.getProducao(resultSet.getInt("idProducao"));
            prod.addTemporada(t);
        }

        resultSet = statement.executeQuery("select *from pessoa_producao");
        Map<Integer, Integer> pessoa_producao = new TreeMap<>();
        while(resultSet.next()) {
            pessoa_producao.put(resultSet.getInt("idPessoa"), resultSet.getInt("idProducao"));
        }
        
        resultSet = statement.executeQuery("select *from ator");
        while(resultSet.next()) {
            Pessoa p = new Ator(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getInt("idade"), resultSet.getInt("tempoDeCarreira"), EnumTempoDeCarreira.ANOS, resultSet.getString("linkBiografia"));
            for (Integer i : pessoa_producao.keySet()) {
                if (i == p.getId()) f.addPessoa(p, pessoa_producao.get(i));
            }
        }

        resultSet = statement.executeQuery("select *from diretor");
        while(resultSet.next()) {
            Pessoa p = new Diretor(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getInt("idade"), resultSet.getInt("nmrDeProducoes"), resultSet.getString("linkBiografia"));
            for (Integer i : pessoa_producao.keySet()) {
                if (i == p.getId()) f.addPessoa(p, pessoa_producao.get(i));
            }
        }
        System.out.println("Downloaded Successfully!");
        return bdEstadoConectado.getInstancia();
    }

}
