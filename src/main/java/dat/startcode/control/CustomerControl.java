package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Customer", value = "/Customer")
public class CustomerControl extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {UserMapper userMapper = new UserMapper(connectionPool);
        HttpSession session = request.getSession();
        session.setAttribute("customers",userMapper.getAllUsers());


        request.getRequestDispatcher("WEB-INF/customer.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int balance = Integer.parseInt(request.getParameter("balance"));
        int userId = Integer.parseInt(request.getParameter("Button"));
        UserMapper userMapper = new UserMapper(connectionPool);
        System.out.println(balance);
        userMapper.updateCustomerBalance(userId,balance);

        doGet(request,response);

    }
}
