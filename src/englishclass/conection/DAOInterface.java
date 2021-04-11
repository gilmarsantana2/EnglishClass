package englishclass.conection;

import java.util.ArrayList;

public interface DAOInterface<T> {

    int incluir(T model);
    boolean excluir(int id);
    boolean alterar(T model);
    T selectById(int id);
    T selectByName(String name);
    ArrayList<T> selectAll();
}
