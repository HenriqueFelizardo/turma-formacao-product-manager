package br.com.cast.turmaformacao.estoquedeprodutos.model.sync;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import br.com.cast.turmaformacao.estoquedeprodutos.controllers.activities.InventoryListActivity;
import br.com.cast.turmaformacao.estoquedeprodutos.model.services.ProductBusinessService;

public class GetProduct extends AsyncTask<Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... params) {
        ProductBusinessService.synchronize();
        return null;
    }

}
