package ifpb.ads.autor.infra;

import ifpb.ads.autor.Autor;
import ifpb.ads.autor.CPF;
import ifpb.ads.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edilva
 */
public class AutoresJDBCImpl implements Autores {

    private Connection connection;

    @Override
    public boolean salvar(Autor autor) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO autor (nome, email, cpf) "
                    + "VALUES(?, ?, ?)");
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getEmail());
            ps.setString(3, autor.getCpf());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutoresJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean remover(Autor autor) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM autor WHERE email = ?");
            ps.setString(1, autor.getEmail());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutoresJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean atualizar(Autor autor) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE autor "
                    + "SET nome = ?, email = ? WHERE cpf = ?");
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getEmail());
            ps.setString(3, autor.getCpf());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutoresJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Autor> listar() {
        List<Autor> autores = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM autor");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                autores.add(dadosDoAutor(rs));
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoresJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autores;
    }

    @Override
    public Autor getAutor(String cpf) {
        Autor autor = null;
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM autor WHERE cpf = ?");
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                autor = dadosDoAutor(rs);
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoresJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autor;
    }

    private Autor dadosDoAutor(ResultSet rs) throws SQLException{
        Autor autor = new Autor();
        autor.setNome(rs.getString("nome"));
        autor.setEmail(rs.getString("email"));
        String cpf = rs.getString("cpf");
        autor.setCpf(cpf);
        return autor;
    }
}
