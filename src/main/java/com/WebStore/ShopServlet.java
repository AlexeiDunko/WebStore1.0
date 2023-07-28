package com.WebStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class ShopServlet<action> extends HttpServlet {
    private DataFlavor request;

    private Item getItemById(String itemId) {
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get session, check if user agreed with terms
        // get name from request
        String name = request.getParameter("name");
        // check if user agreed with terms
        String agree = request.getParameter("agree");
        if (agree == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        // save user's name and agreement to session
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("agreed", true);
        // redirect to shop page
        response.sendRedirect("shop.jsp");

        String action = request.getParameter("action");
        if ("Add Item".equals(action)) {
            // get item from request
            String itemId = request.getParameter("item");
            Item item = getItemById(itemId);
            // get cart from session or create new one
            session = request.getSession();
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }
            // add item to cart
            cart.add(item);
        } else if ("Submit".equals(action)) {
            // redirect to checkout page
            response.sendRedirect("checkout.jsp");
        }
        // redirect back to shop page
        response.sendRedirect("shop.jsp");

    } else if("Submit".void equals(action)){
        // calculate total cost
        HttpSession session;
        session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double total = 0;
        for (Item item : cart) {
            total += item.getPrice();
        }
        // save total to session
        session.setAttribute("total", total);
        // redirect to checkout page
        response.sendRedirect("checkout.jsp");
    }
}


































