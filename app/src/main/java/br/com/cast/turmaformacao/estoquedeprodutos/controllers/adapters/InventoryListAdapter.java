package br.com.cast.turmaformacao.estoquedeprodutos.controllers.adapters;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import br.com.cast.turmaformacao.estoquedeprodutos.R;
import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;

public class InventoryListAdapter extends BaseAdapter {

    private List<Product> productList;
    private Activity context;

    public InventoryListAdapter(List<Product> productList, Activity context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);
        View cardView = context.getLayoutInflater().inflate(R.layout.list_item_iventory, parent, false);

        TextView textViewName = (TextView) cardView.findViewById(R.id.textViewName);
        textViewName.setText(product.getName());

        TextView textViewAmount = (TextView) cardView.findViewById(R.id.textViewAmount);
        textViewAmount.setText(product.getAmount().toString());

        TextView textViewPrice = (TextView) cardView.findViewById(R.id.textViewPrice);
        textViewPrice.setText(product.getPrice().toString());

        TextView textViewImage = (TextView) cardView.findViewById(R.id.textViewImage);
        textViewImage.setText(product.getImage());

        return cardView;
    }
}
