package upload.multipart.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FIleController2 {

    private static final Path LOCAL = Paths.get("src/main/resources/static").toAbsolutePath().normalize();
    private static final Path LOCAL2 = Paths.get("src/main/resources/static");

    @GetMapping
    public ResponseEntity<List<String>> listFiles() throws IOException { //lista os arquivos no diretório local
        return ResponseEntity.ok(Files.list(LOCAL)
                .map(Path::getFileName).map(Path::toString)
                .collect(Collectors.toList()));
    }

    @GetMapping("/download/{fileName:.+}") //olhar curl em resources/curl.txt
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName, HttpServletRequest request) throws IOException {
        try {
            Resource resource = new UrlResource(LOCAL2.resolve(fileName).normalize().toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType == null ? "application/octet-stream" : contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}