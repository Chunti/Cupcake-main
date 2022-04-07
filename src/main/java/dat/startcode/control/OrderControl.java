package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Order", value = "/Order")
public class OrderControl extends HttpServlet {

    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(user.getRole() == 1){
            session.setAttribute("orders",orderMapper.getOrderDataForAdmin());
        }
        else session.setAttribute("orders",orderMapper.getOrderDataForUser(user.getUserId()));


        request.getRequestDispatcher("WEB-INF/order.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
