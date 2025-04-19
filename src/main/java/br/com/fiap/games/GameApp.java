package br.com.fiap.games;

import br.com.fiap.games.model.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class GameApp {

    public static void main(String[] args) {
        Game game1 = new Game();
        game1.setTitulo("Mega Man 1");
        game1.setCategoria("Plataforma");
        game1.setDataLancamentos(LocalDate.of(1987,12,1));
        game1.setFinalizado(true);
        game1.setProdutora("Capcom");
        game1.setValor(128.0);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("games");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(game1);
        em.getTransaction().commit();
        em.close();
    }
}
