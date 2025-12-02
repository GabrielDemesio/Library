package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Library {
    private final List<Book> books;
    private final List<Author> authors;
    private final List<Borrowing> borrowings;

    public Library() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.borrowings = new ArrayList<>();
        seedData();
    }

    private void seedData() {

        Author a1 = new Author(1, "George Orwell");
        Author a2 = new Author(2, "J. R. R. Tolkien");
        Author a3 = new Author(3, "Machado de Assis");
        authors.add(a1);
        authors.add(a2);
        authors.add(a3);

        books.add(new Book(1, "1984", a1, 1984));
        books.add(new Book(2, "A Revolução dos Bichos", a1, 1940));
        books.add(new Book(3, "O Hobbit", a2, 2004));
        books.add(new Book(4, "Dom Casmurro", a3, 1900));
        books.add(new Book(5, "Memórias Póstumas de Brás Cubas", a3, 1800));
    }
    public List<Book> listAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    public Optional<Book> findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public Borrowing registrarEmprestimo(Book book, String nomeUsuario) {
        if (book == null) throw new IllegalArgumentException("Livro inválido");
        if (nomeUsuario == null || nomeUsuario.isBlank()) throw new IllegalArgumentException("Nome do usuário inválido");
        if (!book.isAvailable()) throw new IllegalStateException("Livro indisponível");

        book.setAvailable();
        Borrowing b = new Borrowing(borrowings.size() + 1, book, nomeUsuario.trim(), LocalDateTime.now());
        borrowings.add(b);
        return b;
    }

    public static String normalizarResposta(String s) {
        if (s == null) return "";
        return s.trim().toUpperCase(Locale.ROOT);
    }

    public static int lerInt(Scanner sc) {
        while (true) {
            String raw = sc.nextLine().trim();
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException ignored) {
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }


    public static class Borrowing {
        private final int id;
        private final Book book;
        private final String userName;
        private final LocalDateTime dateTime;

        public Borrowing(int id, Book book, String userName, LocalDateTime dateTime) {
            this.id = id;
            this.book = book;
            this.userName = userName;
            this.dateTime = dateTime;
        }

        public int getId() { return id; }
        public Book getBook() { return book; }
        public String getUserName() { return userName; }
        public LocalDateTime getDateTime() { return dateTime; }
    }
}
