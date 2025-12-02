import models.Book;
import models.Library;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static models.Library.normalizarResposta;
import static models.Library.lerInt;

public class Main {
    public static void main(String[] args) {
    Library library = new Library();
    try (Scanner sc = new Scanner(System.in)) {

        while (true) {
            System.out.print("Deseja ver os livros disponíveis? (SIM/NÃO): ");
            String resp = normalizarResposta(sc.nextLine());

            if (resp.equals("NÃO") || resp.equals("NAO")) {
                System.out.println("Encerrando o sistema. Até mais.");
                return;
            }

            if (!resp.equals("SIM")) {
                System.out.println("Resposta inválida. Digite SIM ou NÃO.");
                continue;
            }

            List<Book> isAvailable = library.listAvailableBooks();
            if (isAvailable.isEmpty()) {
                System.out.println("Não há livros disponíveis no momento.");
                return;
            }

            System.out.println("\nLivros disponíveis:");
            for (Book b : isAvailable) {
                System.out.printf("  [%d] %s — %s%n", b.getId(), b.getTitle(), b.getAuthor().getName());
            }

            System.out.print("\nDigite o ID do livro que deseja pegar emprestado: ");
            int idLivro = lerInt(sc);

            Optional<Book> livroOpt = library.findBookById(idLivro);
            if (livroOpt.isEmpty()) {
                System.out.println("ID inválido ou livro indisponível. Voltando ao início.\n");
                continue;
            }

            System.out.print("Digite seu nome para registrar o empréstimo: ");
            String nome = sc.nextLine().trim();
            if (nome.isBlank()) {
                System.out.println("Nome inválido. Voltando ao início.\n");
                continue;
            }

            Library.Borrowing e = library.registrarEmprestimo(livroOpt.get(), nome);
            System.out.printf(
                    "Empréstimo realizado com sucesso. Livro \"%s\" emprestado para %s (protocolo %d).%n%n",
                    e.getBook().getTitle(),
                    e.getUserName(),
                    e.getId()
            );
        }
    }
    }
}
