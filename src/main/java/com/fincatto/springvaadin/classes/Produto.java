package com.fincatto.springvaadin.classes;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {
    
    private Long id;
    private String urlCapa, titulo, descricao;
    
    public Long getId() {
        return id;
    }
    
    public Produto setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getUrlCapa() {
        return urlCapa;
    }
    
    public Produto setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
        return this;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public Produto setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(urlCapa, produto.urlCapa) && Objects.equals(titulo, produto.titulo) && Objects.equals(descricao, produto.descricao);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, urlCapa, titulo, descricao);
    }
    
    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", urlCapa='" + urlCapa + '\'' + ", titulo='" + titulo + '\'' + ", descricao='" + descricao + '\'' + '}';
    }
}
