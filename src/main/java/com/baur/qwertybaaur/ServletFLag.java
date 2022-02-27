package com.baur.qwertybaaur;

import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.CountryInfoService;
import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.TCountryInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet(name = "ServletFLag", value = "/ServletFLag")
public class ServletFLag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Create(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Create(request, response);

    }

    public void Create(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException {
        CountryInfoService countryIS = new CountryInfoService();
        TCountryInfo tCountryInfo =
                countryIS.getCountryInfoServiceSoap().fullCountryInfo(request.getParameter("countryCode"));

        response.setContentType("text/html; charset=UTF-8");
        ResultSet resultSet = null;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Country</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>" + "Flag of " + tCountryInfo.getSName() + " - </h1>");
        out.println("" +
                "<img src=\"" + tCountryInfo.getSCountryFlag() + "\" alt=\"flag of " + tCountryInfo.getSName() + "\">");
        out.println("</body>");
        out.println("</html>");


    }

}
