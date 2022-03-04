package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;


import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

        private final Repository repo = new Repository();
        private final Product book1 = new Book(1, "Война и мир", 900, "Лев Толстой");
        private final Product book2 = new Book(2, "Ночной дозор", 600, "Сергей Лукьяненко");
        private final Product book3 = new Book(3, "Маскарад", 400, "Терри Пратчетт");
        private final Product smartphone1 = new Smartphone(4, "Redmi 9", 13450, "Xiaomi");
        private final Product smartphone2 = new Smartphone(5, "IPhone 11", 55900, "Apple");

        @Test
        public void shouldTestSaveOneBook() {
            repo.save(book1);

            Product[] expected = new Product[]{book1};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestSaveOneSmartphone() {
            repo.save(smartphone1);

            Product[] expected = new Product[]{smartphone1};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestSaveBookAndSmartphone() {
            repo.save(book1);
            repo.save(smartphone1);

            Product[] expected = new Product[]{book1, smartphone1};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestFindZero() {

            Product[] expected = new Product[]{};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestFindOneBook() {
            repo.save(book1);

            Product[] expected = new Product[]{book1};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestFindOneSmartphone() {
            repo.save(smartphone1);


            Product[] expected = new Product[]{smartphone1};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestFindBookAndSmartphone() {
            repo.save(book1);
            repo.save(smartphone1);

            Product[] expected = new Product[]{book1, smartphone1};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestRemoveBookById() {
            repo.save(book1);
            repo.removeById(1);

            Product[] expected = new Product[]{};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestRemoveSmartphoneById() {
            repo.save(smartphone1);
            repo.removeById(4);

            Product[] expected = new Product[]{};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestRemoveBookAndSmartphoneById() {
            repo.save(book1);
            repo.save(book2);
            repo.save(smartphone1);
            repo.save(smartphone2);

            repo.removeById(2);
            repo.removeById(4);

            Product[] expected = new Product[]{book1, smartphone2};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldTestRemoveNoId() {
            repo.save(book1);
            repo.save(book2);
            repo.save(book3);
            repo.save(smartphone1);
            repo.save(smartphone2);

            repo.removeById(6);

            Product[] expected = {book1, book2, book3, smartphone1, smartphone2};
            Product[] actual = repo.findAll();
            assertArrayEquals(expected, actual);
        }

    @Test
    void shouldTestRemoveByIdIfExists() {
        int id = 2;
        repo.removeById(id);

        Product[] expected = new Product[]{book1, book3, smartphone1, smartphone2};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldTestRemoveByIdIfNotExists() {
        int id = 6;
        assertThrows(NotFoundException.class, () -> repo.removeById(id));
    }
}