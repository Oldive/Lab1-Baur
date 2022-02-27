package com.baur.qwertybaaur;

import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.CountryInfoService;
import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.TCountryInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet(name = "ServletCountryInformation", value = "/ServletCountryInformation")
public class ServletCountryInformation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Create(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Create(request,response);

    }

    public void Create(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CountryInfoService countryInfServ = new CountryInfoService();

        TCountryInfo countryinfo = countryInfServ.getCountryInfoServiceSoap().fullCountryInfo(request.getParameter("CodeofCountry"));

        response.setContentType("text/html; charset=UTF-8");

        ResultSet resultSet = null;

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Country</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>" + "Info of " + countryinfo.getSName() + ": </h1>");
        out.println("<table cellpadding=\"4\">");

        out.print("<tr><td>" + "The name of the country: " + "</td>");
        out.print("<td>" + countryinfo.getSName() + "</td></tr>");
        out.print("<tr><td>" + "Capital name: " + "</td>");
        out.print("<td>" + countryinfo.getSCapitalCity() + "</td></tr>");
        out.print("<tr><td>" + "Telephone code: " + "</td>");
        out.print("<td>" + countryinfo.getSPhoneCode() + "</td></tr>");
        out.print("<tr><td>" + "Continent code: " + "</td>");
        out.print("<td>" + countryinfo.getSContinentCode() + "</td></tr>");
        out.print("<tr><td>" + "Currency code: " + "</td>");
        out.print("<td>" + countryinfo.getSCurrencyISOCode() + "</td></tr>");
        out.print("<tr><td>" + "Flag: " + "</td>");
        out.print("<td>" + "<img src=\"" + countryinfo.getSCountryFlag() + "\" " +
                "alt=\"flag of " + countryinfo.getSName() + "\">" + "</td></tr>");



        out.println("</table>");
        out.println("</body>");
        out.println("</html>");


    }

}
