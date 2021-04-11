package englishclass.conection;

import englishclass.model.UserModel;

import java.util.ArrayList;

public class UserDao extends ConnectionDB implements DAOInterface<UserModel> {

    private final String tableName = "user";

    @Override
    public int incluir(UserModel model) {
        return 0;
    }

    @Override
    public boolean excluir(int id) {
        return false;
    }

    @Override
    public boolean alterar(UserModel model) {
        return false;
    }

    @Override
    public UserModel selectById(int id) {
        return null;
    }

    @Override
    public UserModel selectByName(String name) {
        var model = new UserModel();
        try {
            this.executarSQL("SELECT "
                    + "id, nome, password "
                    + "FROM " + tableName + " WHERE nome = '" + name + "';");
            while (this.getResultSet().next()) {
                model.setId(this.getResultSet().getInt(1));
                model.setNome(this.getResultSet().getString(2));
                model.setPassword(this.getResultSet().getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return model;
    }

    @Override
    public ArrayList<UserModel> selectAll() {
        return null;
    }
}
