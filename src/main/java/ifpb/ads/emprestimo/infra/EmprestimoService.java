
package ifpb.ads.emprestimo.infra;

import ifpb.ads.emprestimo.Emprestimo;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class EmprestimoService{
    
    private Emprestimos emprestimos = new EmprestimosJDBCImpl();

    public void emprestar(Emprestimo emprestimo) {
        this.emprestimos.emprestar(emprestimo);
    }

    public void devolver(Emprestimo emprestimo) {
        this.emprestimos.devolver(emprestimo);
    }

    public void atualizar(Emprestimo emprestimo) {
        this.emprestimos.atualizar(emprestimo);
    }
    
    public List<Emprestimo> list() {
        return this.emprestimos.list();
    }
}
