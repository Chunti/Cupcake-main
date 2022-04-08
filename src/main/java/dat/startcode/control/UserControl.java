package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "User", value = "/User")
public class UserControl extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phone"));
        int balance = Integer.parseInt(request.getParameter("balance"));
        String password = request.getParameter("password");

        UserMapper userMapper = new UserMapper(connectionPool);

        try {
            userMapper.createUser(fullName,email,password,0,phoneNumber,balance);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("login.jsp").forward(request,response);

    }
}
