package web.servlet;

import utils.Methods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/deleteFolder")
public class DeleteFoldersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String goalFolder = request.getParameter("goalFolder");

        File goalFolderFile = new File(goalFolder);

        boolean exists = Methods.deleteFiles(goalFolderFile);


        if (exists){
            request.getRequestDispatcher("/SuccessPage.html").forward(request,response);
        }else {
            request.getRequestDispatcher("/FailPage.html").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
