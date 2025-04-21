package br.com.fiap.games.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "T_GAMES")
public class Game {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_GAMES_SEQ")
    @SequenceGenerator(
            name = "T_GAMES_SEQ",
            sequenceName = "T_GAMES_SEQ",
            allocationSize = 1)
    private Long id;

    private String titulo;

    @Column(name = "DATA_LANCAMENTO")
    private LocalDate dataLancamento;

    private Double valor;

    private String produtora;

    private Boolean finalizado;

    @ManyToOne
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamentos) {
        this.dataLancamento = dataLancamentos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ""
                + "\nTITULO: " + this.titulo + ""
                + "\nPRODUTORA: " + this.produtora + ""
                + "\nCATEGORIA: " + this.categoria.getNomeCategoria() + ""
                + "\nLANÇAMENTO: " + this.dataLancamento + ""
                + "\nFINALIZADO: " + this.finalizado + ""
                + "\nVALOR: " + this.valor;
    }
}
