package controllers.others;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Good;
import utils.DBUtil;

/**
 * Servlet implementation class FollowServlet
 */
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();




            Good g = new Good();

            g = (Good)request.getSession().getAttribute("g");

           long good_count =(long)request.getSession().getAttribute("good_count");

           if(good_count > 0) {

            Good e = em.createNamedQuery("checkGoodReports", Good.class)
                    .setParameter("employee", g.getGoodemployee())
                    .setParameter("report", g.getGoodreport())
            .getSingleResult();






            em.getTransaction().begin();
            em.remove(e);       // データ削除
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "いいね解除しました。");

            response.sendRedirect(request.getContextPath() + "/reports/index");

           } else {

            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
            em.close();


            request.getSession().setAttribute("flush", "いいねしました。");

            response.sendRedirect(request.getContextPath() + "/reports/index");
            }
        }

    }



}






