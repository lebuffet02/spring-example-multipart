package upload.multipart.controller.ex2;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upload.multipart.service.ex2.UploadService2;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("upload2")
public class UploadController2 {

    private final Path fileStorageLocation = Paths.get("src/main/resources/static").toAbsolutePath().normalize();
    @Autowired
    UploadService2 uploadService2;


    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(uploadService2.uploadService(file));
    }

    @GetMapping
    public ResponseEntity<List<String>> listFiles() throws IOException {
        return ResponseEntity.ok(uploadService2.listFilesService());
    }

    @GetMapping("/download/{fileName:.+}") //considera o ponto da extensao do arquivo
    public ResponseEntity<Resource> downloadFile(@PathVariable String file, HttpServletRequest request) throws IOException {
        Path filePath = fileStorageLocation.resolve(file).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        String contentType = request.getServletContext().getMimeType(resource.getFile().getPath());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType == null ? "application/octet-stream" : contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + resource.getFilename() + "\"").body(resource);
    }
}
