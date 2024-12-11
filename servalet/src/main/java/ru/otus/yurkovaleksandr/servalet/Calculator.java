package ru.otus.yurkovaleksandr.servalet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "calculator", value = "/calculator")
public class Calculator extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private String actionCalc(HttpServletRequest req) {
        int oneNumber = Integer.parseInt(req.getParameter("oneNumber"));
        int twoNumber = Integer.parseInt(req.getParameter("twoNumber"));

        if(req.getParameter("action").contentEquals("+"))
            return " = " + (oneNumber + twoNumber);
        if(req.getParameter("action").contentEquals("-"))
            return (" = ") + (oneNumber - twoNumber);

        if(req.getParameter("action").contentEquals("*"))
            return (" = ") + (oneNumber * twoNumber);
        if(req.getParameter("action").contentEquals("/")){
            double one = Double.parseDouble(req.getParameter("oneNumber"));
            return (" = ")+ (one / twoNumber);
        }
        return "";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println("<h1>Результат</h1>");
        resp.getWriter().println(req.getParameter("oneNumber"));

        resp.getWriter().println(req.getParameter("action"));
        resp.getWriter().println(req.getParameter("twoNumber"));
        String result = actionCalc(req);
        if(!result.isEmpty())
            resp.getWriter().println(result);
        else
            resp.getWriter().println("Ошибка !!, такое делать не умею");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/formCalc.html");
        dispatcher.forward(req, resp);
    }
}
