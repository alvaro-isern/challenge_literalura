package com.challenge.challenge_literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;
    private String idiomas;
    private Double numeroDeDescargas;

    public Libro() {
    }

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autor = new Autor(datosLibros.autor().get(0));
        this.idiomas = String.valueOf(datosLibros.idiomas().get(0));
        this.numeroDeDescargas = datosLibros.numeroDeDescargas() != null ? datosLibros.numeroDeDescargas() : 0.0;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return  "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "\nTitulo: " + titulo +
                "\nAutor: " + autor.getNombre() +
                "\nIdiomas: " + idiomas +
                "\nNúmero de descargas: " + numeroDeDescargas +
                "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";

    }
}
