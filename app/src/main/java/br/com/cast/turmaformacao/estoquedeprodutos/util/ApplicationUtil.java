package br.com.cast.turmaformacao.estoquedeprodutos.util;

import android.content.Context;

public class ApplicationUtil {
    private static Context context;

    private ApplicationUtil() {
        super();
    }

    public static void setContext(Context context) {
        ApplicationUtil.context = context;
    }

    public static Context getContext() {
        return ApplicationUtil.context;
    }
}
