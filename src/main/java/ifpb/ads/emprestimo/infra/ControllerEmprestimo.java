
package ifpb.ads.emprestimo.infra;

import ifpb.ads.emprestimo.Emprestimo;
import ifpb.ads.livro.infra.LivroService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Edilva
 */
@Named
@SessionScoped
public class ControllerEmprestimo implements Serializable{
    
    private EmprestimoService service = new EmprestimoService();
    private Emprestimo emprestimo = new Emprestimo();
    private String isbn;
    private boolean editando = false;

    public String emprestar() {
        LivroService livroService = new LivroService();
        this.emprestimo.setLivro(livroService.getLivro(isbn));
        this.service.emprestar(emprestimo);
        this.emprestimo = new Emprestimo();
        return null;
    }

    public String devolver(Emprestimo emprestimo) {
        this.service.devolver(emprestimo);
        this.emprestimo = new Emprestimo();
        return null;
    }

    public String atualizar() {
        this.service.atualizar(emprestimo);
        this.emprestimo = new Emprestimo();
        this.editando = false;
        return null;
    }

    public String editarEmprestimo(Emprestimo emprestimoAtualizar) {
        this.emprestimo = emprestimoAtualizar;
        this.editando = true;
        return null;
    }
    
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    
    public List<Emprestimo> getTodosOsEmprestimos() {
        return this.service.list();
    }
    
    
}
