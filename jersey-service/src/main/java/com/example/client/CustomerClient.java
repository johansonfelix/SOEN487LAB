package com.example.client;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.util.Scanner;

public class CustomerClient {

    public static void main(String[] args) throws InterruptedException {
        createCustomer("Nick",24);
        createCustomer("Ali",30);
        createCustomer("Hamed",26);

        System.out.println(getCustomers());

        Thread.sleep(5000);

        replaceCustomer(0,"Kosta",28);

        Thread.sleep(5000);

        System.out.println(getCustomers());

        for(int i = 1; i<=3; i++){
            deleteCustomer(i);
            Thread.sleep(1000);
        }

        System.out.println(getCustomers());






    }

    public static String getCustomers() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/myapp/customer");

            CloseableHttpResponse response = client.execute(httpGet);
            return readResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get Customers";
        }

    }

    private static String getACustomer(int id) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(String.format("http://localhost:8080/myapp/customer/%d", id));

            CloseableHttpResponse response = httpClient.execute(httpGet);

            return readResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get customer " + id;
        }
    }

    private static String readResponse(CloseableHttpResponse response) throws IOException {
        Scanner scanner = new Scanner(response.getEntity().getContent());
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static void createCustomer(String name, int age) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/myapp/customer/%s/%d", name, age));
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void replaceCustomer(int id, String name, int age) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/myapp/customer/%d/%s/%d", id, name, age));
            client.execute(httpPut);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteCustomer(int id) {
        try (CloseableHttpClient client = HttpClients.createDefault()){
            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/myapp/customer/%d", id));
            CloseableHttpResponse deleteResponse = client.execute(httpDelete);
            deleteResponse.close();

        }

        catch(IOException e){
            e.printStackTrace();

        }

    }
}

