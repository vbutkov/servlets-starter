package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        resp.setHeader("Content-Disposition", "attachment; filename=\"pom.xml\"");

        //final var resources = Files.readAllBytes(Path.of("resources", "pom.xml"));
        try (final var outputStream = resp.getOutputStream();
             final var stream = DownloadServlet.class.getClassLoader().getResourceAsStream("pom.xml");
        ) {
            outputStream.write(stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
