package Adaptador;

import java.sql.SQLException;

import Estados.bdEstado;

public final class OperarNoBanco implements AdaptadorOperarNoBanco {
    private OperarNoBanco(){}
    private static OperarNoBanco instancia = new OperarNoBanco();
    
    public static OperarNoBanco getInstancia() {
        return instancia;
    }
    
    @Override
    public bdEstado operar(bdEstado estado) throws SQLException {
        return estado.processar();
    }
}
