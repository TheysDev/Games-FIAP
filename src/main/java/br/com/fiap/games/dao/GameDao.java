package br.com.fiap.games.dao;

import br.com.fiap.games.model.Game;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GameDao {

    private EntityManager em;

    public GameDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Game game) {
        em.persist(game);
    }

    public  void atualizar (Game game) {
        em.merge(game);
    }

    public void remover(Game game) {
        Game gameRemover= em.find(Game.class, game.getId());
        em.remove(gameRemover);
    }

    public Game buscarGamePeloId(Long id) {
        return em.find(Game.class, id);
    }

    public List<Game> listarTodosOsGames() {
        String jpqlQuery= " SELECT g FROM Game g ORDER BY g.titulo ASC";
        return em.createQuery(jpqlQuery, Game.class).getResultList();
    }

    public List<Game> buscarGamePeloNome(String titulo){
        String jpqlQuery = "SELECT g FROM Game g WHERE g.titulo = :titulo ";
        return em.createQuery(jpqlQuery, Game.class)
                .setParameter("titulo", titulo)
                .getResultList();
    }

    public List<Game> buscarGamesPorFaixaDeValores(Double valorInicial, Double valorFinal){
        String jpqlQuery = "SELECT g FROM Game g WHERE g.valor " +
                "BETWEEN :valorInicial AND :valorFinal ORDER BY g.titulo ASC";
        return em.createQuery(jpqlQuery, Game.class)
                .setParameter("valorInicial", valorInicial)
                .setParameter("valorFinal", valorFinal)
                .getResultList();
    }
}
