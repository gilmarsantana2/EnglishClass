package englishclass.controller;

import englishclass.util.Criptografia;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Validator {

    public static <T> T valid(Object model) {
        Class<?> classe = model.getClass();
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(Password.class)){
                try {
                    field.setAccessible(true);
                    field.set(model, Criptografia.hash((String) field.get(model)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T) model;
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Password {
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface StartDate {
    }

}
