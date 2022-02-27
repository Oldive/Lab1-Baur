package com.baur.qwertybaaur;

import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(name = "ServletCountry", value = "/ServletCountry")
public class ServletCountry extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Create(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Create(request, response);

    }

    public void Create(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CountryInfoService countryInfServ = new CountryInfoService();

        CountryInfoServiceSoapType countryInfServSoap = countryInfServ.getCountryInfoServiceSoap();

        ArrayOftContinent arrayOftContinent = countryInfServSoap.listOfContinentsByName();

        List<TContinent> tContinent = arrayOftContinent.getTContinent();

        ArrayOftCountryCodeAndName arrayCodName = countryInfServSoap.listOfCountryNamesByName();

        List<TCountryCodeAndName> tCountryCodeAndNames = arrayCodName.getTCountryCodeAndName();

        response.setContentType("text/html; charset=UTF-8");
        ResultSet resultSet = null;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Country</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>List of сountries</h1>");
        out.println("<table cellpadding=\"4\">");
        out.println("<tr>\n" +
                "        <th>id</th>\n" +
                "        <th>Code</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Flag</th>\n" +
                "        <th>Information</th>\n" +
                "        <th>Languages </th>\n" +

                "    </tr>");
        System.out.println(tContinent.toString());
        for (TCountryCodeAndName countryCodeAndName : tCountryCodeAndNames) {
            int lastIndex = tCountryCodeAndNames.lastIndexOf(countryCodeAndName);

            out.print("<tr>");
            out.print(" <td>" + lastIndex + "</td>");
            out.print(" <td>" + countryCodeAndName.getSISOCode() + "</td>");
            out.print(" <td>" + countryCodeAndName.getSName() + "</td>");
            out.print(" <td><a href=\"/flag?countryCode=" + countryCodeAndName.getSISOCode() + "\"> " + "flag" + "</a></td>");
            out.print(" <td><a href=\"/inf?countryCode=" + countryCodeAndName.getSISOCode() + "\"> " + "inf" + "</a></td>");
            out.print(" <td><a href=\"/languages?countryCode=" + countryCodeAndName.getSISOCode() + "\"> " + "languages" + "</a></td>");
            out.print("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
