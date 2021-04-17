package englishclass.conection;

import englishclass.model.UserModel;

import java.util.ArrayList;

public class UserDao extends ConnectionDB implements DAOInterface<UserModel> {

    @Override
    public int incluir(UserModel model) {
        return this.insertSQL(SQLQueries.insertInto(model));
    }

    @Override
    public boolean excluir(UserModel model) {
        return this.executarUpdateDeleteSQL(SQLQueries.delete(model));
    }

    @Override
    public boolean alterar(UserModel model) {
        return this.executarUpdateDeleteSQL(SQLQueries.update(model));
    }

    @Override
    public UserModel selectById(UserModel model) {
        var userModel = new UserModel();
        try {
            this.selectSQL(SQLQueries.selectById(model));
            while (this.getResultSet().next()) {
                userModel.setId(this.getResultSet().getInt(1));
                userModel.setUserName(this.getResultSet().getString(2));
                userModel.setPassword(this.getResultSet().getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
        return userModel;
    }

    @Override
    public UserModel selectByName(String name) {
        var userModel = new UserModel();
        try {
            this.selectSQL(SQLQueries.selectSpecial("user", "nome ='" + name + "'"));
            while (this.getResultSet().next()) {
                userModel.setId(this.getResultSet().getInt(1));
                userModel.setUserName(this.getResultSet().getString(2));
                userModel.setPassword(this.getResultSet().getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
        return userModel;
    }

    @Override
    public ArrayList<UserModel> selectAll() {
        ArrayList<UserModel> lista = new ArrayList<>();
        UserModel model;
        try {
            this.selectSQL(SQLQueries.selectAll("user"));
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
