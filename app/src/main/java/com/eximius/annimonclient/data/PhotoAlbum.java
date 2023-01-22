package com.eximius.annimonclient.data;

public class PhotoAlbum {

    private int id;
    private String urlPhoto;
    private String name;
    private int numPhotos;
    private String author;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumPhotos(int numPhotos) {
        this.numPhotos = numPhotos;
    }

    public int getNumPhotos() {
        return numPhotos;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
