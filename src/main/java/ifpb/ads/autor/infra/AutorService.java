
package ifpb.ads.autor.infra;

import ifpb.ads.autor.Autor;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class AutorService {
    
    private final Autores autores = new AutoresJDBCImpl();
    
    public void salvar(Autor autor) {
        this.autores.salvar(autor);
    }

    public void remover(Autor autor) {
        this.autores.remover(autor);
    }

    public void atualizar(Autor autor) {
        this.autores.atualizar(autor);
    }

    public List<Autor> todosOsClientes() {
        return this.autores.listar();
    }
    
    public Autor getAutor(String cpf) {
        return this.autores.getAutor(cpf);
    }
    
}
