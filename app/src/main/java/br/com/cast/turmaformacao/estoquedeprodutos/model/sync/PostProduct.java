package br.com.cast.turmaformacao.estoquedeprodutos.model.sync;

import android.os.AsyncTask;

import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;
import br.com.cast.turmaformacao.estoquedeprodutos.model.http.ProductService;

public class PostProduct extends AsyncTask<Product, Void, Void> {


    @Override
    protected Void doInBackground(Product... products) {
        ProductService.postProducts(products[0]);
        return null;
    }
}
