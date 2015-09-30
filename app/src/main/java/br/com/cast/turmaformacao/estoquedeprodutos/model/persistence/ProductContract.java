package br.com.cast.turmaformacao.estoquedeprodutos.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;

public final class ProductContract {
    public static final String TABLE = "produto";
    public static final String ID = "id";
    public static final String WEB_ID = "web_id";
    public static final String DATE = "date";
    public static final String IMAGE = "image";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String MIN_AMOUNT = "min_amount";
    public static final String PRICE = "price";
    public static final String[] COLUNS = {ID, WEB_ID, DATE, IMAGE, NAME, DESCRIPTION, AMOUNT, MIN_AMOUNT, PRICE};

    private ProductContract() {
        super();
    }

    public static String getTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(WEB_ID + " INTEGER, ");
        create.append(DATE + " INTEGER, ");
        create.append(IMAGE + " TEXT, ");
        create.append(NAME + " TEXT , ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(AMOUNT + " INTEGER , ");
        create.append(MIN_AMOUNT + " INTEGER , ");
        create.append(PRICE + " REAL  ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductContract.ID, product.get_id());
        contentValues.put(ProductContract.WEB_ID, product.getWeb_id());
        contentValues.put(ProductContract.DATE, product.getDate());
        contentValues.put(ProductContract.IMAGE, product.getImage());
        contentValues.put(ProductContract.NAME, product.getName());
        contentValues.put(ProductContract.DESCRIPTION, product.getDescript());
        contentValues.put(ProductContract.AMOUNT, product.getAmount());
        contentValues.put(ProductContract.MIN_AMOUNT, product.getMinAmount());
        contentValues.put(ProductContract.PRICE, product.getPrice());

        return contentValues;
    }

    public static Product getProduct(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Product product = new Product();
            product.set_id(cursor.getLong(cursor.getColumnIndex(ProductContract.ID)));
            product.setWeb_id(cursor.getLong(cursor.getColumnIndex(ProductContract.WEB_ID)));
            product.setDate(cursor.getLong(cursor.getColumnIndex(ProductContract.DATE)));
            product.setImage(cursor.getString(cursor.getColumnIndex(ProductContract.IMAGE)));
            product.setName(cursor.getString(cursor.getColumnIndex(ProductContract.NAME)));
            product.setDescript(cursor.getString(cursor.getColumnIndex(ProductContract.DESCRIPTION)));
            product.setAmount(cursor.getLong(cursor.getColumnIndex(ProductContract.AMOUNT)));
            product.setMinAmount(cursor.getLong(cursor.getColumnIndex(ProductContract.MIN_AMOUNT)));
            product.setPrice(cursor.getDouble(cursor.getColumnIndex(ProductContract.PRICE)));

            return product;
        }
        return null;
    }

    public static List<Product> getProducts(Cursor cursor) {
        List<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            products.add(getProduct(cursor));
        }
        return products;
    }
}
