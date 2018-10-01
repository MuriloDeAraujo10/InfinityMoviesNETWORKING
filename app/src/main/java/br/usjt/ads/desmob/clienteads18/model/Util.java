package br.usjt.ads.desmob.clienteads18.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.lang.reflect.Field;
import br.usjt.ads.desmob.clienteads18.R;

public class Util {

    public static Drawable getDrawable(Context context, String nome){
        Class<?> c= R.drawable.class;
        try {
            Field idField = c.getField(nome);
            int id = idField.getInt(idField);
            Drawable imagem = context.getResources().getDrawable(id, null);
            return imagem;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return context.getResources().getDrawable(R.drawable.emoji);
    }
}