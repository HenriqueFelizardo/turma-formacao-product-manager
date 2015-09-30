package br.com.cast.turmaformacao.estoquedeprodutos.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;

public final class ProductRepository {
    private ProductRepository() {
        super();
    }

    public static void save(Product product) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = ProductContract.getContentValues(product);

        if (product.get_id() == null) {
            db.insert(ProductContract.TABLE, null, values);
        } else {
            String where = ProductContract.ID + " = ? ";
            String[] params = {product.get_id().toString()};
            db.update(ProductContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = ProductContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(ProductContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Product> getAll() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ProductContract.TABLE, ProductContract.COLUNS, null, null, null, null, ProductContract.ID);
        List<Product> values = ProductContract.getProducts(cursor);

        while (cursor.moveToNext()) {
            Product product = new Product();
            values.add(product);
        }

        db.close();
        databaseHelper.close();
        return values;
    }

    public static Long getId(String id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = ProductContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(ProductContract.TABLE, ProductContract.COLUNS, where, params, null, null, null);

        Product product = ProductContract.getProduct(cursor);
        db.close();
        databaseHelper.close();

        return product == null ? null : product.get_id();
    }
}
