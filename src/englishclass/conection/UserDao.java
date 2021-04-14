package englishclass.conection;

import englishclass.model.UserModel;

import java.util.ArrayList;

public class UserDao extends ConnectionDB implements DAOInterface<UserModel> {

    private final String tableName = "user";

    @Override
    public int incluir(UserModel model) {
        return insertSQL("INSERT INTO " + tableName +
                "(nome, password) VALUES ("
                + "'" + model.getUserName() + "', "
                + "'" + model.getPassword() + "');"
        );
    }

    @Override
    public boolean excluir(int id) {
        return executarUpdateDeleteSQL("DELETE FROM " + tableName + " WHERE id = '" + id + "'");
    }

    @Override
    public boolean alterar(UserModel model) {
        return this.executarUpdateDeleteSQL("UPDATE " + tableName + " SET "
                + "nome = '" + model.getUserName() + "', "
                + "password = '" + model.getPassword() + "'"
                + " WHERE id = '" + model.getId() + "';"
        );
    }

    @Override
    public UserModel selectById(int id) {
        var model = new UserModel();
        try {
            this.executarSQL("SELECT "
                    + "id, nome, password "
                    + "FROM " + tableName + " WHERE id = '" + id + "';");
            while (this.getResultSet().next()) {
                model.setId(this.getResultSet().getInt(1));
                model.setUserName(this.getResultSet().getString(2));
                model.setPassword(this.getResultSet().getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
        return model;
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
                model.setUserName(this.getResultSet().getString(2));
                model.setPassword(this.getResultSet().getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
        return model;
    }

    @Override
    public ArrayList<UserModel> selectAll() {
        ArrayList<UserModel> lista = new ArrayList<>();
        UserModel model;
        try {
            this.executarSQL("select * from " + tableName + ";");
            while (this.getResultSet().next()) {
                model = new UserModel();
                model.setId(this.getResultSet().getInt(1));
                model.setUserName(this.getResultSet().getString(2));
                model.setPassword(this.getResultSet().getString(3));
                lista.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
        return lista;
    }
}
