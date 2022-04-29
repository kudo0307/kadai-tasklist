package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        // Taskのインスタンス生成
        Task t = new Task();

        // タスクの内容
        String content = "hello";
        t.setContent(content);

        Timestamp crnTime = new Timestamp(System.currentTimeMillis());
        t.setCreated_at(crnTime);
        t.setUpdated_at(crnTime);

        // データベースに保存
        em.persist(t);
        em.getTransaction().commit();

        em.close();
    }

}
