package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Bottom;
import dat.startcode.model.entities.Topping;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BottomMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ToppingMapper;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Bestil", value = "/Bestil")
public class Bestil extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
        UserMapper.createUserTest(connectionPool);


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        HttpSession session = request.getSession();

        String bottom = request.getParameter("bottom");
        String topping = request.getParameter("topping");

    }
}
