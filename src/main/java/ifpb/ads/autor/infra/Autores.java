
package ifpb.ads.autor.infra;

import ifpb.ads.autor.Autor;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface Autores {
    boolean salvar(Autor autor);
    boolean remover(Autor autor);
    boolean atualizar(Autor autor);
    List<Autor> listar();
    Autor getAutor(String cpf);
}
