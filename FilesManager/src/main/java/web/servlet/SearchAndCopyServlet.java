package web.servlet;

import utils.Methods;

import java.io.*;
import java.lang.invoke.MethodHandle;

@javax.servlet.annotation.WebServlet("/searchAndCopy")
public class SearchAndCopyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        String searchFolder = request.getParameter("searchFolder");
        String copyFolder = request.getParameter("copyFolder");
        String searchWords = request.getParameter("searchWords");

        //此功能需要三个参数：要搜索的文件夹(searchFolder)，要复制到的目标文件夹(goalFolder)，需要的文件文件名中包含的关键字(searchWords)

        File searchFolderFile = new File(searchFolder);

        File goalFolderFile = new File(copyFolder);

        boolean flag = goalFolderFile.exists() && searchFolderFile.exists();

        if (!flag){
            response.sendRedirect(request.getContextPath()+"/FailPage.html");
        }else {
            if (type.equals("file")){
                Methods.searchFolder1(searchFolderFile, goalFolderFile, searchWords);//该方法内部使用copyFile方法，使该方法能够同时完成搜索与复制
            }else {
                Methods.searchFolder2(searchFolderFile, goalFolderFile, searchWords);//该方法内部使用copyFile方法，使该方法能够同时完成搜索与复制
            }

            if (goalFolderFile.list().length!=0){
                request.getRequestDispatcher("/SuccessPage.html").forward(request,response);
            }else {
                request.getRequestDispatcher("/FailPage.html").forward(request,response);
            }
        }


    }



    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
