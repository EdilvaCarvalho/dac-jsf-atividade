
package ifpb.ads.emprestimo.infra;

import ifpb.ads.conexao.Conexao;
import ifpb.ads.emprestimo.Emprestimo;
import ifpb.ads.emprestimo.LivroSituacao;
import ifpb.ads.livro.infra.LivroService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edilva
 */
public class EmprestimosJDBCImpl implements Emprestimos{
    
    private Connection connection;

    @Override
    public boolean emprestar(Emprestimo emprestimo) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "emprestimo(data_emprestimo, nome_cliente, situacao, isbn_livro) "
                    + "VALUES(?, ?, ?, ?)");
            ps.setDate(1, Date.valueOf(emprestimo.getDataDoEmprestimo()));
            ps.setString(2, emprestimo.getNomeDoCliente());
            ps.setString(3, emprestimo.getSituacao().name());
            ps.setString(4, emprestimo.getLivro().getISBN());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean devolver(Emprestimo emprestimo) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE emprestimo "
                    + "SET situacao = ? WHERE id = ?");
            ps.setString(1, LivroSituacao.DEVOLVIDO.name());
            ps.setInt(2, emprestimo.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean atualizar(Emprestimo emprestimo) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE emprestimo "
                    + "SET data_emprestimo = ?, nome_cliente = ?, situacao = ?, isbn_livro = ? "
                    + "WHERE id = ?");
            ps.setDate(1, Date.valueOf(emprestimo.getDataDoEmprestimo()));
            ps.setString(2, emprestimo.getNomeDoCliente());
            ps.setString(3, emprestimo.getSituacao().name());
            ps.setString(4, emprestimo.getLivro().getISBN());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Emprestimo> list() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM emprestimo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                emprestimos.add(dadosDoEmprestimo(rs));
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimosJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimos;
    }

    private Emprestimo dadosDoEmprestimo(ResultSet rs) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataDoEmprestimo(LocalDate.parse(rs.getString("data_emprestimo")));
        emprestimo.setId(rs.getInt("id"));
        emprestimo.setNomeDoCliente(rs.getString("nome_cliente"));
        emprestimo.setSituacao(LivroSituacao.valueOf(rs.getString("situacao")));
        LivroService livroService = new LivroService();
        emprestimo.setLivro(livroService.getLivro(rs.getString("isbn_livro")));
        return emprestimo;
    }
    
}
