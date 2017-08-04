
package ifpb.ads.livro.infra;

import ifpb.ads.livro.Livro;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class LivroService {
    
    private final Livros livros = new LivrosJDBCImpl();
    
    public void salvar(Livro livro) {
        this.livros.salvar(livro);
    }

    public void remover(Livro livro) {
        this.livros.remover(livro);
    }

    public void atualizar(Livro livro) {
        this.livros.atualizar(livro);
    }

    public List<Livro> todosOsLivros() {
        return this.livros.listar();
    }
    
    public void salvarAutor(String isbn, String cpf){
        this.livros.salvarAutor(isbn, cpf);
    }
    
    public Livro getLivro(String isbn){
        return this.livros.getLivro(isbn);
    }
}
