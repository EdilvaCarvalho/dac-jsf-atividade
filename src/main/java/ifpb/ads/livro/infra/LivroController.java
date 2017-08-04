package ifpb.ads.livro.infra;


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
public class LivroController implements Serializable {

    private Livro livro = new Livro();
    private LivroService service = new LivroService();
    private String a = "";

    private boolean editando = false;

    public String salvarLivro() {
        this.service.salvar(livro);
        this.livro = new Livro();
        return null;
    }

    public String removerLivro(Livro livroRemover) {
        this.service.remover(livroRemover);
        return null;
    }

    public String atualizar() {
        this.service.atualizar(livro);
        this.livro = new Livro();
        this.editando = false;
        return null;
    }

    public String editarLivro(Livro livroAtualizar) {
        this.livro = livroAtualizar;
        this.editando = true;
        return null;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getTodosOsLivros() {
        return this.service.todosOsLivros();
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }


    public String pegaLivro(Livro l) {
        this.livro = l;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("livro1", livro);
        return "livro_autor";
    }
    
    public String adicionarAutor(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.livro = (Livro) session.getAttribute("livro1");
        service.salvarAutor(this.livro.getISBN(), a);
        this.livro = new Livro();
        return "livro";
    }

    public String getAutor() {
        return a;
    }

    public void setAutor(String a) {
        this.a = a;
    }

}
