package com.jaxrs.gestionfinance.resources;

import com.jaxrs.gestionfinance.model.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private static List<Transaction> transactions = new ArrayList<>();
    private static AtomicInteger idCounter = new AtomicInteger();

    // Create
    @POST
    public Response createTransaction(Transaction transaction) {
        transaction.setId(idCounter.incrementAndGet());
        transactions.add(transaction);
        return Response.status(Response.Status.CREATED).entity(transaction).build();
    }

    // Read all
    @GET
    public Response getAllTransactions() {
        return Response.ok(transactions).build();
    }

    // Read by ID
    @GET
    @Path("/{id}")
    public Response getTransactionById(@PathParam("id") int id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                return Response.ok(transaction).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Update
    @PUT
    @Path("/{id}")
    public Response updateTransaction(@PathParam("id") int id, Transaction updatedTransaction) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                transaction.setDescription(updatedTransaction.getDescription());
                transaction.setAmount(updatedTransaction.getAmount());
                return Response.ok(transaction).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Delete
    @DELETE
    @Path("/{id}")
    public Response deleteTransaction(@PathParam("id") int id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                transactions.remove(transaction);
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}