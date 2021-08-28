package controllers.others;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class othersindex
 */
@WebServlet("/follow/index")
public class FollowIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");


        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }


        List<Report> reports = em.createNamedQuery("getMyFollowAllReports", Report.class)
                .setParameter("employee", login_employee)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();



        List<Employee> followers = em.createNamedQuery("getMyFollowerAllEmployees", Employee.class)
                .setParameter("employee", login_employee)
                .getResultList();


        List<Employee> followees = em.createNamedQuery("getMyFolloweeAllEmployees", Employee.class)
                .setParameter("employee", login_employee)
                .getResultList();


        long reports_count = (long)em.createNamedQuery("getMyFollowAllReportsCount", Long.class)
                .setParameter("employee", login_employee)
                .getSingleResult();

        em.close();

        request.setAttribute("reports", reports);
        request.setAttribute("followers", followers);
        request.setAttribute("followees", followees);
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("page", page);


        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/others/follow.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */


}
