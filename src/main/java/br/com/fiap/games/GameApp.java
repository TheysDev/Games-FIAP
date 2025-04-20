package br.com.fiap.games;

import br.com.fiap.games.dao.GameDao;
import br.com.fiap.games.model.Game;
import br.com.fiap.games.utils.Conexao;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class GameApp {

    public static void main(String[] args) {

        EntityManager em = Conexao.getEntityManager();

        //cadastrar(em);
        //pesquisar(em);
        //listarTodosOsGames(em);
        //buscarGamePeloNome(em);
        //buscarGamesPorFaixaDeValores(em);

    }

    public static void buscarGamesPorFaixaDeValores(EntityManager em) {
        GameDao gameDao = new GameDao(em);
        List<Game> games = gameDao.buscarGamesPorFaixaDeValores(150.0,300.0);

        for (Game game : games){
            System.out.println(game);
            System.out.println("--------------------");
        }
    }


    public static void buscarGamePeloNome(EntityManager em) {
        GameDao gameDao = new GameDao(em);
        List<Game> games = gameDao.buscarGamePeloNome("mega man 2".toUpperCase());

        for (Game game : games){
            System.out.println(game);
            System.out.println("--------------------");
        }
    }

    public static void listarTodosOsGames(EntityManager em) {
       GameDao gameDao = new GameDao(em);
       List<Game> games = gameDao.listarTodosOsGames();

       for (Game game : games){
           System.out.println(game);
           System.out.println("--------------------");
       }
    }

    public static void pesquisar(EntityManager em) {

        GameDao gameDao = new GameDao(em);

        em.getTransaction().begin();
        Game gameEncontrado = gameDao.buscarGamePeloId(5L);

        if(gameEncontrado == null) {
            System.out.println("Game não encontrado!");
        }else {
            System.out.println(gameEncontrado.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    public static void cadastrar(EntityManager em){
        Game game1 = new Game();
        game1.setTitulo("BATLETOADS");
        game1.setCategoria("LUTA");
        game1.setDataLancamento(LocalDate.of(1991,6,1));
        game1.setFinalizado(true);
        game1.setProdutora("TRADEWEST, RARE");
        game1.setValor(99.89);

        GameDao gameDao = new GameDao(em);

        em.getTransaction().begin();
        gameDao.salvar(game1);
        em.getTransaction().commit();
        em.close();
    }

}
