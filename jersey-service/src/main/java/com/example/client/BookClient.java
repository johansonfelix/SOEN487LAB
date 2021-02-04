package com.example.client;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;



public class BookClient {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Adding 3 books...\n");
        Thread.sleep(1000);
        createBook("How-to-kill-a-mockingbird", "Harper-Lee","9780060935467");
        Thread.sleep(1000);
        createBook("The-Lightning-Thief", "Rick-Riordan","9780786838653");
        Thread.sleep(1000);
        createBook("Moby-Dick", "Herman-Melville","9780553213119");

        System.out.println("Books Created!");

        System.out.println("\nPrinting Books added:");
        System.out.print(getBook("How-to-kill-a-mockingbird"));
        System.out.print(getBook("The-Lightning-Thief"));
        System.out.print(getBook("Moby-Dick"));

    }


    private static String getBook(String title) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(String.format("http://localhost:8080/myapp/Library/%s",title));
            CloseableHttpResponse response = client.execute(httpGet);

            return readResponse(response);
        }
        catch (IOException e){
            e.printStackTrace();
            return "Failed to find book";
        }

    }

    public static void createBook(String title, String author, String isbn){
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/myapp/Library/%s/%s/%s",title,author,isbn));
            client.execute(httpPut);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String readResponse(CloseableHttpResponse response) throws IOException {
        Scanner scanner = new Scanner(response.getEntity().getContent());
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
            stringBuilder.append("\n");
        }
        response.close();
        return stringBuilder.toString();
    }
}