
package ifpb.ads.emprestimo.infra;

import ifpb.ads.emprestimo.Emprestimo;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface Emprestimos {
   boolean emprestar(Emprestimo emprestimo);
   boolean devolver(Emprestimo emprestimo);
   boolean atualizar(Emprestimo emprestimo);
   List<Emprestimo> list();
}
