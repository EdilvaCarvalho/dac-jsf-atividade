
package ifpb.ads.emprestimo.infra;

import ifpb.ads.emprestimo.Emprestimo;
import ifpb.ads.emprestimo.LivroSituacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class EmprestimosEmMemoria implements Emprestimos{
    
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @Override
    public boolean emprestar(Emprestimo emprestimo) {
        emprestimo.setId(emprestimos.size() + 1);
        return this.emprestimos.add(emprestimo);
    }

    @Override
    public boolean devolver(Emprestimo emprestimo) {
        for (Emprestimo emp : emprestimos) {
            if(emp.getId() == emprestimo.getId()){
                emp.setSituacao(LivroSituacao.DEVOLVIDO);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean atualizar(Emprestimo emprestimo) {
        for (Emprestimo emp : emprestimos) {
            if(emp.getId() == emprestimo.getId()){
                emp.setDataDoEmprestimo(emprestimo.getDataDoEmprestimo());
                emp.setLivro(emprestimo.getLivro());
                emp.setNomeDoCliente(emprestimo.getNomeDoCliente());
                emp.setSituacao(emprestimo.getSituacao());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Emprestimo> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
