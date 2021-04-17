package englishclass.conection;

import java.lang.reflect.Field;

public class SQLQuerys {

    public static String insertInto(Object model, String table){
        Class<?> classe = model.getClass();
        String sql= "INSERT INTO " + table + " (";
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(SQLCollum.class)){
                SQLCollum att = field.getAnnotation(SQLCollum.class);
                sql += att.name() + ",";
            }
        }
        //remove a ultima virgula
        sql = sql.substring(0, sql.lastIndexOf(",")) + ") VALUES (";

        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(SQLCollum.class)){
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
        sql = sql.substring(0, sql.length()-1) + ");";
        return sql;
    }

}
