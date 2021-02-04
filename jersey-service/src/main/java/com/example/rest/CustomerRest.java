package com.example.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("customer")
public class CustomerRest {

    private static ArrayList<Customer> customers = new ArrayList<>();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCustomers() {
        return customers.stream().map(Objects::toString).collect(Collectors.joining(".\n"));
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String getCustomerList(@PathParam("id") int id) {
        Customer customer = customers.stream().filter(customer1 -> customer1.getId() == id)
                .findFirst()
                .orElse(null);
        if (customer != null) {
            return customer.toString();
        } else {
            return "Customer not found!";
        }
    }

    @POST
    @Path("{name}/{age}")
    public Response createCustomer(@PathParam("name") String name, @PathParam("age") int age){
        Customer newCustomer = new Customer(name, age);
        customers.add(newCustomer);
        return Response
                .status(Response.Status.CREATED)
                .entity(String.format("Customer created: %s %d",name,age))
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

    @PUT
    @Path("{id}/{name}/{age}")
    public void modifyCustomer(@PathParam("id") int id, @PathParam("name") String name, @PathParam("age") int age) {
        deleteCustomer(id);
        createCustomer(name, age);
    }

    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        customers = customers.stream().filter(customer -> customer.getId() != id)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void printCustomers() {
        for(Customer customer: customers) {
            System.out.println(customer);
        }
    }
}
