
package ifpb.ads.autor.infra;

import ifpb.ads.autor.Autor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class AutoresEmMemoria implements Autores{
    
    private List<Autor> autores = new ArrayList<>();

    @Override
    public boolean salvar(Autor autor) {
        return this.autores.add(autor);
    }

    @Override
    public boolean remover(Autor autor) {
        return this.autores.remove(autor);
    }

    @Override
    public boolean atualizar(Autor autor) {
        for (Autor a : autores) {
            if(a.getCpf() == autor.getCpf()){
                a.setNome(autor.getNome());
                a.setEmail(autor.getEmail());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Autor> listar() {
        return this.autores;
    }

    @Override
    public Autor getAutor(String cpf) {
        for (Autor a : autores) {
            if(a.getCpf().equals(cpf)){
                return a;
            }
        }
        return null;
    }
    
}
