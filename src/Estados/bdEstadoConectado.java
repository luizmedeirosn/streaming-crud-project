package Estados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import App.App;
import DataBase.DataBase;
import Entidades.Pessoa.Ator;
import Entidades.Pessoa.Diretor;
import Entidades.Pessoa.Pessoa;
import Entidades.Producao.Filme;
import Entidades.Producao.Producao;
import Entidades.Producao.Serie;
import Entidades.Producao.Temporada;
import Servicos.Fachada;

public final class bdEstadoConectado implements bdEstado {
    private bdEstadoConectado() {}
    private static bdEstadoConectado instancia = null;
    private static Connection connBD = DataBase.getConnection();
    private Fachada f = Fachada.getFachada();

    public static bdEstadoConectado getInstancia() {
        if(instancia == null) {
            instancia = new bdEstadoConectado();
        }
        return instancia;
    }

    @Override
    public bdEstado processar() throws SQLException {
        refreshBanco();
        for (Producao prod : App.getProducoes()) {
            if(prod.getId().toString().charAt(0) == '3') salvarFilme(prod.getId());
            else {
                salvarSerie(prod.getId());
                Serie s = (Serie) prod;
                for (Temporada t : s.getTemporadas()) {
                    salvarTemporada(t, s.getId());
                }
            }

            for (Pessoa p : prod.getPessoas()) {
                if(p.getId().toString().charAt(0) == '1') salvarAtor((Ator) p);
                else salvarDiretor((Diretor) p);
                salvarPessoaProducao(p.getId(), prod.getId());
            }
        }
        System.out.println("Uploaded Successfully!");
        return bdEstadoDesconectado.getInstancia();
    }
    
    private void salvarFilme(Integer id) throws SQLException {
        Producao prod = f.getProducao(id);
        PreparedStatement statement = connBD.prepareStatement(
                                    "insert into filme " +
                                    "(id, nome, linkProducao, distribuicao, estreia, horaDoCadastro, duracao) " +
                                    "VALUES " +
                                    "(?, ?, ?, ?, ?, ?, ?)"
            );
        statement.setInt(1, prod.getId());
        statement.setString(2, prod.getNome());
        statement.setString(3, prod.getLinkProducao());
        statement.setString(4, prod.getDistribuicao());
        statement.setString(5, prod.getEstreia());
        statement.setString(6, prod.getHoraDoCadastro());
        Filme f = (Filme) prod;
        statement.setString(7, f.getDuracao());
        statement.executeUpdate();
        statement.close();
    }

    private void salvarSerie(Integer id) throws SQLException {
        Producao prod = f.getProducao(id);
        PreparedStatement statement = connBD.prepareStatement(
                                    "insert into serie " +
                                    "(id, nome, linkProducao, distribuicao, estreia, horaDoCadastro, nmrDeTemporadas) " +
                                    "VALUES " +
                                    "(?, ?, ?, ?, ?, ?, ?)"
            );
        statement.setInt(1, prod.getId());
        statement.setString(2, prod.getNome());
        statement.setString(3, prod.getLinkProducao());
        statement.setString(4, prod.getDistribuicao());
        statement.setString(5, prod.getEstreia());
        statement.setString(6, prod.getHoraDoCadastro());
        Serie s = (Serie) prod;
        statement.setInt(7, s.getNmrDeTemporadas());
        statement.executeUpdate();
        statement.close();
    }

    private void salvarTemporada(Temporada t, Integer idSerie) throws SQLException {
        PreparedStatement statement = connBD.prepareStatement(
                                    "insert into temporada " +
                                    "(id, linkTemporada, idProducao) " +
                                    "VALUES " +
                                    "(?, ?, ?)"
            );
        statement.setInt(1, t.getNmrDaTemporada());
        statement.setString(2, t.getLinkDaTemporada());
        statement.setInt(3, idSerie);
        statement.executeUpdate();
        statement.close();
    }

    private void salvarAtor(Ator a) throws SQLException {
        PreparedStatement statement = connBD.prepareStatement(
                                    "insert into ator " +
                                    "(id, nome, idade, tempoDeCarreira, linkBiografia, horaDoCadastro) " +
                                    "VALUES " +
                                    "(?, ?, ?, ?, ?, ?)"
            );
        statement.setInt(1, a.getId());
        statement.setString(2, a.getNome());
        statement.setInt(3, a.getIdade());
        statement.setInt(4, a.getTempoDeCarreira());
        statement.setString(5, a.getLinkBiografia());
        statement.setString(6, a.getHoraDoCadastro());
        statement.executeUpdate();
        statement.close();
    }

    private void salvarDiretor(Diretor d) throws SQLException {
        PreparedStatement statement = connBD.prepareStatement(
                                    "insert into diretor " +
                                    "(id, nome, idade, nmrDeProducoes, linkBiografia, horaDoCadastro) " +
                                    "VALUES " +
                                    "(?, ?, ?, ?, ?, ?)"
            );
        statement.setInt(1, d.getId());
        statement.setString(2, d.getNome());
        statement.setInt(3, d.getIdade());
        statement.setInt(4, d.getNmrDeProducoes());
        statement.setString(5, d.getLinkBiografia());
        statement.setString(6, d.getHoraDoCadastro());
        statement.executeUpdate();
        statement.close();
    }

    private void salvarPessoaProducao(Integer idPessoa, Integer idProducao) throws SQLException {
        PreparedStatement statement = connBD.prepareStatement(
                                    "insert into pessoa_producao " +
                                    "(idPessoa, idProducao) " +
                                    "VALUES " +
                                    "(?, ?)"
        );
        statement.setInt(1, idPessoa);
        statement.setInt(2, idProducao);
        statement.executeUpdate();
        statement.close();
    }

    private void refreshBanco() throws SQLException {
        PreparedStatement statement = connBD.prepareStatement("delete from filme ");
        statement.executeUpdate();

        statement = connBD.prepareStatement("delete from serie ");
        statement.executeUpdate();

        statement = connBD.prepareStatement("delete from ator ");
        statement.executeUpdate();

        statement = connBD.prepareStatement("delete from diretor ");
        statement.executeUpdate();

        statement = connBD.prepareStatement("delete from pessoa_producao ");
        statement.executeUpdate();

        statement = connBD.prepareStatement("delete from temporada ");
        statement.executeUpdate();

        statement.close();
    }
}
