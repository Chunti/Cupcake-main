package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.*;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Bestil", value = "/Bestil")
public class Bestil extends HttpServlet {


    HttpSession session;
    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        if( (int) session.getAttribute("orderId") == 0){
            User user = (User) session.getAttribute("user");
            int orderId = orderMapper.createOrder(user.getUserId());
            session.setAttribute("orderId", orderId);
        }

        BottomMapper bottomMapper = new BottomMapper(connectionPool);
        ToppingMapper toppingMapper = new ToppingMapper(connectionPool);
        ArrayList<Bottom> bottomArrayList = null;
        ArrayList<Topping> toppingArrayList = null;
        try {
            bottomArrayList = bottomMapper.getBottomData();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            toppingArrayList = toppingMapper.getToppingData();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("bottoms", bottomArrayList);
        servletContext.setAttribute("topping", toppingArrayList);
        System.out.println(bottomArrayList);
        System.out.println(toppingArrayList);

        request.getRequestDispatcher("WEB-INF/bestil.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        int bottom = Integer.parseInt(request.getParameter("bottom"))+1;
        int topping = Integer.parseInt(request.getParameter("topping"))+1;
        int amount = Integer.parseInt(request.getParameter("number"));

        session = request.getSession();
        int orderId = (int) session.getAttribute("orderId");

        OrderMapper orderMapper = new OrderMapper(connectionPool);
        orderMapper.createOrderline(orderId,bottom,topping,amount);
        request.getRequestDispatcher("WEB-INF/bestil.jsp").forward(request, response);


    }
}
