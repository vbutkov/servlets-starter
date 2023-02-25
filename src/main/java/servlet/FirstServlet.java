package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setHeader("token", "12345");

        final var paramValue = req.getParameter("param");
        final var parameterMap = req.getParameterMap();


        final var headerNames = req.getHeaderNames();
        try (var writer = resp.getWriter()) {
            while (headerNames.hasMoreElements()) {
                final var header = headerNames.nextElement();
                System.out.println(req.getHeader(header));
                writer.write("<h2>");
                writer.write(req.getHeader(header));
                writer.write("</h2>");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        //final var parameterMap = req.getParameterMap();
        //System.out.println(parameterMap);

        try (final var reader = req.getReader();
             final var lines = reader.lines();) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}


