package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.product.Product;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ShopRepository;

public class ShopRepositoryTest {
    ShopRepository repository = new ShopRepository();

    Product product1 = new Product(1, "Крем для лица", 1000);
    Product product2 = new Product(12, "Охлаждающая эмульсия", 1500);
    Product product3 = new Product(123, "Скраб для лица", 800);
    Product product4 = new Product(1234, "Расческа", 600);

    @Test
    public void removeByIdExistingProduct() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.add(product4);
        repository.removeById(123);

        Product[] expected = {product1, product2, product4};
        Product[] actual = repository.getSave();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdNonExistingProduct() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.add(product4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(23);
        });
    }

    @Test
    public void addNewProduct() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.add(product4);

        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repository.getSave();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddProductWithDuplicateId () {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.add(product4);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.addNewProduct(product1);
        });
    }

}

