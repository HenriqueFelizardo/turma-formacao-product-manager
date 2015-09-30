package br.com.cast.turmaformacao.estoquedeprodutos;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import br.com.cast.turmaformacao.estoquedeprodutos.util.ApplicationUtil;

public class InventoryManagerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
