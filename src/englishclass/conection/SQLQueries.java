package englishclass.conection;

import englishclass.conection.annotation.PrimaryKey;
import englishclass.conection.annotation.TableCollumn;
import englishclass.conection.annotation.TableName;

import java.lang.reflect.Field;

public class SQLQueries {

    public static String insertInto(Object model) {
        Class<?> classe = model.getClass();
        String sql = "INSERT INTO " + getTableName(model) + " (";
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(TableCollumn.class)) {
                TableCollumn att = field.getAnnotation(TableCollumn.class);
                sql += att.name() + ",";
            }
        }
        //remove a ultima virgula
        sql = sql.substring(0, sql.lastIndexOf(",")) + ") VALUES (";

        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(TableCollumn.class)) {
                field.setAccessible(true);
                try {
                    try {
                        sql += "'" + field.get(model).toString() + "',";
                    } catch (NullPointerException e) {
                        sql += "default,";
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //remove as ulimas aspas
        sql = sql.substring(0, sql.length() - 1) + ");";
        return sql;
    }

    public static String delete(Object model) {
        return "DELETE FROM " + getTableName(model) + " WHERE " + getPrimaryKey(model) + ";";
    }

    public static String update(Object model) {
        Class<?> classe = model.getClass();
        String sql = "UPDATE " + getTableName(model) + " SET ";
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(TableCollumn.class)) {
                field.setAccessible(true);
                TableCollumn collum = field.getAnnotation(TableCollumn.class);
                try {
                    try {
                        sql += collum.name() + " = '" + field.get(model) + "', ";
                    } catch (NullPointerException e) {
                        sql += collum.name() + " = default, ";
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //remove a ultima virgula
        sql = sql.substring(0, sql.lastIndexOf(",")) + " WHERE " + getPrimaryKey(model) + ";";
        return sql;
    }

    public static String selectById(Object model) {
        return "SELECT * FROM " + getTableName(model) + " WHERE " + getPrimaryKey(model) + ";";
    }

    public static String selectSpecial(String tableName, String search) {
        return "SELECT * FROM " + tableName + " WHERE " + search + ";";
    }

    public static String selectAll(String tableName) {
        return "SELECT * FROM " + tableName + ";";
    }

    private static String getTableName(Object model) {
        Class<?> classe = model.getClass();
        String tableName = "";
        if (classe.isAnnotationPresent(TableName.class)) {
            TableName table = classe.getAnnotation(TableName.class);
            tableName = table.table();
        } else throw new NullPointerException();
        return tableName;
    }

    private static String getPrimaryKey(Object model) {
        Class<?> classe = model.getClass();
        String key = "";
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                field.setAccessible(true);
                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                try {
                    key = primaryKey.key() + " = '" + field.getInt(model) + "'";
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else throw new NullPointerException();
        }
        return key;
    }

}
