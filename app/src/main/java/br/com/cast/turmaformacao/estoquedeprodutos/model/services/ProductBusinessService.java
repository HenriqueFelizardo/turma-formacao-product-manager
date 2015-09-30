package br.com.cast.turmaformacao.estoquedeprodutos.model.services;

import java.util.List;

import br.com.cast.turmaformacao.estoquedeprodutos.model.entities.Product;
import br.com.cast.turmaformacao.estoquedeprodutos.model.http.ProductService;
import br.com.cast.turmaformacao.estoquedeprodutos.model.persistence.ProductRepository;

public final class ProductBusinessService {
    private ProductBusinessService() {
        super();
    }

    public static List<Product> findAll() {
        return ProductRepository.getAll();
    }

    public static void save(Product product) {
        ProductRepository.save(product);
    }

    public static void delete(Product selectedProduct) {
        ProductRepository.delete(selectedProduct.get_id());
    }

    public static void synchronize() {
        List<Product> webProducts = ProductService.getProducts();

        for(Product p: webProducts){
            Long id = ProductRepository.getId(p.getWeb_id().toString());
            p.set_id(id == null ? null : id);
            save(p);
        }
    }
}
