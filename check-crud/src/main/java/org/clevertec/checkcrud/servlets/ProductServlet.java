package org.clevertec.checkcrud.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clevertec.checkcrud.model.Product;
import org.clevertec.checkcrud.service.ProductService;
import org.clevertec.checkcrud.service.ProductServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ProductServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        String id = req.getParameter("id");
        Product product = productService.getById(Integer.parseInt(id));
        printWriter.println(objectMapper.writeValueAsString(product));
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter output = resp.getWriter();
        String id = req.getParameter("id");
        boolean result = productService.deleteById(Integer.parseInt(id));
        if (result) {
            output.print("The product with id " + Integer.parseInt(id) + " deleted");
        } else {
            output.print("The product with id " + Integer.parseInt(id) + " doesn't exist");
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int paramId = Integer.parseInt(req.getParameter("id"));
        Product product = objectMapper.readValue(reqBody, Product.class);
        product.setId(paramId);
        productService.update(product);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Product product = objectMapper.readValue(reqBody, Product.class);
        productService.save(product);
        PrintWriter output = resp.getWriter();
        output.print("The product with id " + product.getId() + " created");
    }
}
