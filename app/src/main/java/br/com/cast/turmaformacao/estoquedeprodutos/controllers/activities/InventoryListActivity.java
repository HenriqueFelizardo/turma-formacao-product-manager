package br.com.cast.turmaformacao.estoquedeprodutos.controllers.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.cast.turmaformacao.estoquedeprodutos.R;
import br.com.cast.turmaformacao.estoquedeprodutos.controllers.adapters.InventoryListAdapter;
import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;
import br.com.cast.turmaformacao.estoquedeprodutos.model.http.ProductService;
import br.com.cast.turmaformacao.estoquedeprodutos.model.services.ProductBusinessService;
import br.com.cast.turmaformacao.estoquedeprodutos.model.sync.GetProduct;


public class InventoryListActivity extends AppCompatActivity {

    private ListView listViewInventory;
    private Product product;
    private FloatingActionButton fab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        bindListViewInventory();
        bindFloatingButton();
    }

    private void bindFloatingButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listViewInventory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNewProductActivity = new Intent(InventoryListActivity.this, NewProductActivity.class);
                startActivity(goToNewProductActivity);
            }
        });
    }

    private void bindListViewInventory() {
        listViewInventory = (ListView) findViewById(R.id.listViewInventory);
        registerForContextMenu(listViewInventory);
        listViewInventory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                InventoryListAdapter adapter = (InventoryListAdapter) listViewInventory.getAdapter();
                product = adapter.getItem(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_product_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                onMenuEditClick();
                break;
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToNewProduct = new Intent(InventoryListActivity.this, NewProductActivity.class);
        goToNewProduct.putExtra(NewProductActivity.PARAM_PRODUCT, product);
        startActivity(goToNewProduct);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(InventoryListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProductBusinessService.delete(product);
                        product = null;
                        String message = getString(R.string.msg_delete_successfull);
                        Toast.makeText(InventoryListActivity.this, message, Toast.LENGTH_LONG).show();
                        updateProductList();
                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_update:
                try {
                    onMenuUpdateClick();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuUpdateClick() throws ExecutionException, InterruptedException {
        new GetProduct().execute();
    }

    @Override
    protected void onResume() {
        updateProductList();
        super.onResume();
    }

    private void updateProductList() {
        List<Product> values = ProductBusinessService.findAll();
        listViewInventory.setAdapter(new InventoryListAdapter(values, this));
        InventoryListAdapter adapter = (InventoryListAdapter) listViewInventory.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
