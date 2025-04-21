package br.com.fiap.games.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "T_CATEGORIAS")
public class Categoria {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_CATEGORIAS_SEQ")
    @SequenceGenerator(
            name = "T_CATEGORIAS_SEQ",
            sequenceName = "T_CATEGORIAS_SEQ",
            allocationSize = 1)
    private Long id;

    @Column(name = "nome_categoria")
    private String nomeCategoria;

    @OneToMany(mappedBy = "categoria")
    private List<Game> games;

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", games=" + games +
                '}';
    }
}

