package com.baur.qwertybaaur;

import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.CountryInfoService;
import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.TCountryInfo;
import com.baur.qwertybaaur.kz.iitu.soap.ws.client.generated.TLanguage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(name = "ServletCountryLanguage", value = "/ServletCountryLanguage")
public class ServletCountryLanguage extends HttpServlet {
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

        out.println("<h1>" + "List of language in " + countryinfo.getSName() + ": </h1>");
        List<TLanguage> tLanguages = countryinfo.getLanguages().getTLanguage();
        System.out.println(tLanguages);

        int counter = 0;
        for (TLanguage tl : tLanguages) {
            counter++;
            out.println(counter + "." + tl.getSName());
        }
        if (counter == 0) {
            out.println("no special languages");
        }
        out.println("</body>");
        out.println("</html>");

    }
}
