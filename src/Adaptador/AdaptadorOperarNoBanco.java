package Adaptador;

import java.sql.SQLException;

import Estados.bdEstado;

public interface AdaptadorOperarNoBanco {
    public bdEstado operar(bdEstado estado) throws SQLException;
}
