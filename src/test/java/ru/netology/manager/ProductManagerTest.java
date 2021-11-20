package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    private Product item1 = new Book(1, 1000, "Lewis Carroll", "Alice in Wonderland");
    private Product item2 = new Book(2, 118, "Darya Dontsova", "Poker with a Shark");
    private Product item3 = new Smartphone(3, 119999, "Apple", "13 Pro Max");
    private Product item4 = new Smartphone(4, 1999, "Nokia", "3310");

    @BeforeEach
    void setUp() {
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.save(item4);
    }

    @Test
    public void shouldGetAll() {
        Product[] expected = new Product[]{item1, item2, item3, item4};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAuthor() {
        Product[] expected = new Product[]{item1};
        Product[] actual = manager.searchBy("Lewis Carroll");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByBookTtile() {
        Product[] expected = new Product[]{item2};
        Product[] actual = manager.searchBy("Poker with a Shark");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAuthorWithoutResult() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("J.K. Rowling");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhoneName() {
        Product[] expected = new Product[]{item3};
        Product[] actual = manager.searchBy("13 Pro Max");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhoneBrand() {
        Product[] expected = new Product[]{item4};
        Product[] actual = manager.searchBy("Nokia");
        assertArrayEquals(expected, actual);
    }
}