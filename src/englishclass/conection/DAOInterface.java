package englishclass.conection;

import java.util.ArrayList;

public interface DAOInterface<T> {

    int incluir(T model);
    boolean excluir(T model);
    boolean alterar(T model);
    T selectById(T model);
    T selectByName(String name);
    ArrayList<T> selectAll();
}
