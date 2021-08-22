package controllers.others;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowServlet
 */
@WebServlet("/FollowServlet")
public class FollowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub







        request.getSession().setAttribute("flush", "フォローしました。");

        response.sendRedirect(request.getContextPath() + "/reports/index");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();




            Follow f = new Follow();

            f = (Follow)request.getSession().getAttribute("f");

           long follow_count =(long)request.getSession().getAttribute("follow_count");

           if(follow_count > 0) {

            Follow e = em.createNamedQuery("checkMyFollowReports", Follow.class)
                    .setParameter("follower", f.getFollower())
                    .setParameter("followee", f.getFollowee())
            .getSingleResult();






            em.getTransaction().begin();
            em.remove(e);       // データ削除
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "フォロー解除しました。");

            response.sendRedirect(request.getContextPath() + "/reports/index");

           } else {

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();


            request.getSession().setAttribute("flush", "フォローしました。");

            response.sendRedirect(request.getContextPath() + "/reports/index");
            }
        }

    }



}






