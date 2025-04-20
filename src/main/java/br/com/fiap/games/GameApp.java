package br.com.fiap.games;

import br.com.fiap.games.dao.GameDao;
import br.com.fiap.games.model.Game;
import br.com.fiap.games.utils.Conexao;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;

public class GameApp {

    public static void main(String[] args) {
        Game game1 = new Game();
        game1.setTitulo("Batletoads");
        game1.setCategoria("Luta");
        game1.setDataLancamentos(LocalDate.of(1992,8,1));
        game1.setFinalizado(true);
        game1.setProdutora("TradeWest");
        game1.setValor(99.89);

        EntityManager em = Conexao.getEntityManager();
        GameDao gameDao = new GameDao(em);

        em.getTransaction().begin();
        gameDao.salvar(game1);
        em.getTransaction().commit();
        em.close();
    }
}
