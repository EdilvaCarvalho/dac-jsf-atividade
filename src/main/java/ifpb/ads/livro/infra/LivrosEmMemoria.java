
package ifpb.ads.livro.infra;

import ifpb.ads.livro.Livro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class LivrosEmMemoria implements Livros{
    
    private List<Livro> livros = new ArrayList<>();

    @Override
    public boolean salvar(Livro livro) {
        return this.livros.add(livro);
    }

    @Override
    public boolean remover(Livro livro) {
        return this.livros.remove(livro);
    }

    @Override
    public boolean atualizar(Livro livro) {
        for (Livro l : livros) {
            if(l.getISBN().equals(livro.getISBN())){
                l.setDescricao(livro.getDescricao());
                l.setEdicao(livro.getEdicao());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Livro> listar() {
        return this.livros;
    }

    @Override
    public Livro getLivro(String isbn) {
        for (Livro l : livros) {
            if(l.getISBN().equals(isbn)){
                return l;
            }
        }
        return null;
    }

    @Override
    public void salvarAutor(String isbn, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
