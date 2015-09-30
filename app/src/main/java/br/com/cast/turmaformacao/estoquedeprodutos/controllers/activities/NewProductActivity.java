package br.com.cast.turmaformacao.estoquedeprodutos.controllers.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.estoquedeprodutos.R;
import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;
import br.com.cast.turmaformacao.estoquedeprodutos.model.http.ProductService;
import br.com.cast.turmaformacao.estoquedeprodutos.model.services.ProductBusinessService;
import br.com.cast.turmaformacao.estoquedeprodutos.model.sync.PostProduct;

public class NewProductActivity extends AppCompatActivity {

    public static final String PARAM_PRODUCT = "PARAM_PRODUCT";

    private EditText editTextName;
    private EditText editTextDescript;
    private EditText editTextAmount;
    private EditText editTextMinAmount;
    private EditText editTextPrice;
    private Button buttonInclude;
    private Product product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        initProduct();

        bindEditTextName();
        bindEditTextDescript();
        bindEditTextAmount();
        bindEditTextMinAmount();
        bindEditTextPrice();
        bindButtonInclude();
    }

    private void initProduct() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.product = extras.getParcelable(PARAM_PRODUCT);
        }
        this.product = product == null ? new Product() : product;
    }

    private void bindButtonInclude() {
        buttonInclude = (Button) findViewById(R.id.buttonInclude);
        buttonInclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binProduct();
                ProductBusinessService.save(product);
                new PostProduct().execute(product);
                Toast.makeText(NewProductActivity.this, R.string.msg_save, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void binProduct() {

        product.setName(editTextName.getText().toString());
        product.setDescript(editTextDescript.getText().toString());
        product.setAmount(Long.parseLong(editTextAmount.getText().toString()));
        product.setMinAmount(Long.parseLong(editTextMinAmount.getText().toString()));
        product.setPrice(Double.parseDouble(editTextPrice.getText().toString()));
    }

    private void bindEditTextPrice() {
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextPrice.setText(product.getPrice() == null ? "" : product.getPrice().toString());
    }

    private void bindEditTextMinAmount() {
        editTextMinAmount = (EditText) findViewById(R.id.editTextMinAmount);
        editTextMinAmount.setText(product.getMinAmount() == null ? "" : product.getMinAmount().toString());
    }

    private void bindEditTextAmount() {
        editTextAmount = (EditText) findViewById(R.id.editTextAmount);
        editTextAmount.setText(product.getAmount() == null ? "" : product.getAmount().toString());
    }

    private void bindEditTextDescript() {
        editTextDescript = (EditText) findViewById(R.id.editTextDescript);
        editTextDescript.setText(product.getDescript() == null ? "" : product.getDescript());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(product.getName() == null ? "" : product.getName());
    }
}
