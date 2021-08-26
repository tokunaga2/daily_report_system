package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import models.Good;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());

        Follow f = new Follow();


        f.setFollower((Employee)request.getSession().getAttribute("login_employee"));
        f.setFollowee(r.getEmployee());


        long follow_count = (long)em.createNamedQuery("checkMyFollowReportsCount", Long.class)
                .setParameter("followee", f.getFollowee())
                .setParameter("follower", f.getFollower())
                .getSingleResult();


        request.getSession().setAttribute("follow_count",follow_count );
        request.getSession().setAttribute("f",f );







        Good g = new Good();


        g.setGoodemployee((Employee)request.getSession().getAttribute("login_employee"));
        g.setGoodreport(r);


        long good_count = (long)em.createNamedQuery("checkGoodReportsCount", Long.class)
                    .setParameter("employee", g.getGoodemployee())
                    .setParameter("report", g.getGoodreport())
                    .getSingleResult();




        request.getSession().setAttribute("good_count",good_count );
        request.getSession().setAttribute("g",g );





        em.close();







        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }

}