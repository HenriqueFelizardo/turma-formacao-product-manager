package br.com.cast.turmaformacao.estoquedeprodutos.model.http;

import android.location.Address;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;

public final class ProductService {
    private static final String URL = "http://10.11.21.193:4000/api/v1/products";

    private ProductService() {
        super();
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        try {
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.e("getProducts", "Codigo de retorno: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class);
                products = objectMapper.readValue(inputStream, collectionType);
            }
        } catch (Exception e) {
            Log.e(ProductService.class.getName(), e.getMessage());
        }

        return products;
    }

    public static void postProducts(Product product) {

        try {
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/json");

            OutputStream os = conn.getOutputStream();
            new ObjectMapper().writeValue(os, product);

            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code: " + responseCode);
            }

            conn.disconnect();

        } catch (Exception e) {
            Log.e(ProductService.class.getName(), e.getMessage());
        }

    }

}
