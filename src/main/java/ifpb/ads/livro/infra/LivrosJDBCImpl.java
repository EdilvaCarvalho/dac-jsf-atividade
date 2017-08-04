package ifpb.ads.livro.infra;

import ifpb.ads.autor.Autor;
import ifpb.ads.autor.infra.AutorService;
import ifpb.ads.autor.infra.Autores;
import ifpb.ads.autor.infra.AutoresJDBCImpl;
import ifpb.ads.conexao.Conexao;
import ifpb.ads.livro.Livro;
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
public class LivrosJDBCImpl implements Livros {

    private Connection connection;

    @Override
    public boolean salvar(Livro livro) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "livro(isbn, descricao, edicao) VALUES(?, ?, ?)");
            ps.setString(1, livro.getISBN());
            ps.setString(2, livro.getDescricao());
            ps.setString(3, livro.getEdicao());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LivrosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void salvarAutor(String isbn, String cpf) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "autor_livro(isbn_livro, cpf_autor) VALUES(?, ?)");
            ps.setString(1, isbn);
            ps.setString(2, cpf);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean remover(Livro livro) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM livro WHERE isbn = ?");
            ps.setString(1, livro.getISBN());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LivrosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean atualizar(Livro livro) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE livro "
                    + "SET descricao = ?, edicao = ? WHERE isbn = ?");
            ps.setString(1, livro.getDescricao());
            ps.setString(2, livro.getEdicao());
            ps.setString(3, livro.getISBN());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LivrosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM livro");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                livros.add(dadosDoLivro(rs));
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    @Override
    public Livro getLivro(String isbn) {
        Livro livro = null;
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM livro WHERE isbn = ?");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                livro = dadosDoLivro(rs);
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livro;
    }

    private Livro dadosDoLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setISBN(rs.getString("isbn"));
        livro.setDescricao(rs.getString("descricao"));
        livro.setEdicao(rs.getString("edicao"));
        List<Autor> autores = autoresDoLivro(rs.getString("isbn"));
        livro.setAutores(autores);
        return livro;
    }

    private List<Autor> autoresDoLivro(String isbn) {
        List<Autor> autores = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM (livro l JOIN autor_livro a ON "
                    + "l.isbn = a.isbn_livro) WHERE l.isbn = ?");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            Autores dao = new AutoresJDBCImpl();

            while (rs.next()) {
                autores.add(dao.getAutor(rs.getString("cpf_autor")));
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autores;
    }

}
