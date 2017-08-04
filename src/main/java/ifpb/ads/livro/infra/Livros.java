
package ifpb.ads.livro.infra;

import ifpb.ads.livro.Livro;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface Livros {
    boolean salvar(Livro livro);
    boolean remover(Livro livro);
    boolean atualizar(Livro livro);
    List<Livro> listar();
    Livro getLivro(String isbn);
    void salvarAutor(String isbn, String cpf);
}
