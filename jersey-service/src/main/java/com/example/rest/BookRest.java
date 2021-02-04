package com.example.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("Library")
public class BookRest {

    private static ArrayList<Book> books = new ArrayList<>();

    @PUT
    @Consumes("application/xml")
    public Response createBook(Book book) {

        ArrayList<Book> newBooks = new ArrayList<>();

        for (Book oldBook : books) {
            if (!oldBook.getIsbn().equals(book.getIsbn())) {
                newBooks.add(oldBook);
            }
        }

        books = newBooks;
        books.add(book);

        return Response.status(Response.Status.CREATED)
                .entity(String.format("New Book created: %s by %s [%s]",book.getTitle(),book.getAuthor(), book.getIsbn()))
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

    @GET
    @Produces("application/xml")
    @Path("{title}")
    public Book getBook(@PathParam("title") String title) {

        return books.stream().filter(book -> book.getTitle().equals(title))
                .findFirst().orElse(null);

           }

    @POST
    public Response createBookFromForm(@FormParam("title") String title, @FormParam("author") String author, @FormParam("ISBN") String isbn){
        books.add(new Book(title, author, isbn));

        return Response.status(Response.Status.CREATED)
                .entity(String.format("New Book created: %s by %s [%s]",title,author, isbn))
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }


}
