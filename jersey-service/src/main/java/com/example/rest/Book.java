package com.example.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @XmlElement
    private String title;
    @XmlElement
    private String author;
    @XmlElement
    private String isbn;



    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;


    }
        public Book(Book book){
            this.title = book.title;
            this.author = book.author;
            this.isbn = book.isbn;
        }

    public Book(){ }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String toString(){
        return String.format("Title: %s, Author:%s, ISBN:%s",title,author,isbn);
    }


}
