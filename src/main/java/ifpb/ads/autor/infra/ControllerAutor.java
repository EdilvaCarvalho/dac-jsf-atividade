
package ifpb.ads.autor.infra;

import ifpb.ads.autor.Autor;
import ifpb.ads.livro.Livro;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edilva
 */
@Named
@SessionScoped
public class ControllerAutor implements Serializable{
    
    private Autor autor = new Autor();
    private AutorService service = new AutorService();
    
    private boolean editando = false;

    public String salvarAutor() {
        this.service.salvar(autor);
        this.autor = new Autor();
        return null;
    }

    public String removerAutor(Autor autorRemover) {
        this.service.remover(autorRemover);
        return null;
    }

    public String atualizar() {
        this.service.atualizar(autor);
        this.autor = new Autor();
        this.editando = false;
        return null;
    }

    public String editarAutor(Autor autorAtualizar) {
        this.autor = autorAtualizar;
        this.editando = true;
        return null;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Autor> getTodosOsAutores() {
        return this.service.todosOsClientes();
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    

    
}
