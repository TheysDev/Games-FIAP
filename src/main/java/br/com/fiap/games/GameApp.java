package br.com.fiap.games;

import br.com.fiap.games.dao.CategoriaDao;
import br.com.fiap.games.dao.GameDao;
import br.com.fiap.games.model.Categoria;
import br.com.fiap.games.model.Game;
import br.com.fiap.games.utils.Conexao;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class GameApp {

    public static void main(String[] args) {

        EntityManager em = Conexao.getEntityManager();

        cadastrar(em);
        //pesquisar(em);
        //listarTodosOsGames(em);
        //buscarGamePeloNome(em);
        //buscarGamesPorFaixaDeValores(em);
        //buscarGamePelaProdutora(em);
        //buscarGamesFinalizadosOuNaoFinalizados(em);
        //buscarGamesPorFaixaDeDataLancamento(em);
    }

    public static void buscarGamesPorFaixaDeDataLancamento(EntityManager em){
        GameDao gameDao = new GameDao(em);
        List<Game> games = gameDao.buscarGamesPorFaixaDeDataLancamento(LocalDate.of(1986,2,1), LocalDate.of(1987,12,31));

        for (Game game : games){
            System.out.println("---------------------");
            System.out.println(game);
        }
    }

    public static void buscarGamesFinalizadosOuNaoFinalizados(EntityManager em){
        GameDao gameDao = new GameDao(em);
        List<Game> games = gameDao.buscarGamesFinalizadosOuNaoFinalizados(false);

        for (Game game : games){
            System.out.println("---------------------");
            System.out.println(game);
        }
    }

    public static void buscarGamePelaProdutora(EntityManager em){
        GameDao gameDao = new GameDao(em);
        List<Game> games = gameDao.buscarGamePelaProdutora("NINTENDO");

        for (Game game : games){
            System.out.println("---------------------");
            System.out.println(game);
        }
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

        // CRIANDO UMA CATEGORIA
        Categoria luta = new Categoria();
        luta.setNomeCategoria("LUTA");

        // CRIANDO UMA INSTANCIA DA CATEGORIA-DAO
        CategoriaDao categoriaDao = new CategoriaDao(em);

        // INICIANDO UMA TRANSAÇÃO DE DADOS NO BANCO
        em.getTransaction().begin();

        // SALVANDO A NOVA CATEGORIA NO BANCO DE DADOS
        categoriaDao.salvar(luta);

        // CRIANDO UM GAME DA CATEGORIA LUTA
        Game game1 = new Game();
        game1.setTitulo("Street Fighter II");
        game1.setCategoria(luta);
        game1.setDataLancamento(LocalDate.of(1992,2,1));
        game1.setFinalizado(true);
        game1.setProdutora("Capcom");
        game1.setValor(399.99);

        // CRIANDO UMA INSTANCIA DA GAME-DAO
        GameDao gameDao = new GameDao(em);

        // SALVANDO O NOVO GAME NO BANCO
        gameDao.salvar(game1);

        // SICRONIZANDO O BANCO COM TODAS AS ALTERAÇÕES
        em.getTransaction().commit();

        // FECHANDO O ENTITY MANEGER
        em.close();
    }

}
