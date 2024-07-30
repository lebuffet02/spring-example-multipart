package upload.multipart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upload.multipart.dto.FileDTO;
import upload.multipart.service.FileService1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
public class FileController1 {

    @Autowired
    FileService1 service1;

    private static final Path RESOURCES = Paths.get("src/main/resources/static").toAbsolutePath().normalize();
    private static final String LOCAL = "src/main/resources/static/";

    @PostMapping("/upload") //para salvar no banco
    public ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return ResponseEntity.ok((!ObjectUtils.isEmpty(service1.upload(file)) ? "Arquivo salvo com sucesso." : "Deu erro ao salvar o arquivo."));
    }

    @PostMapping("/upload2") //upload da imagem em /resources/static
    public ResponseEntity<String> uploadDocuments(@RequestParam("file") MultipartFile file) {
        try {
            File doc = new File(LOCAL.concat(file.getOriginalFilename()));
            file.getInputStream().transferTo(new FileOutputStream(doc, true));
            return ResponseEntity.ok(String.format("%s", "Upload realizado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(String.format("%s", "Deu erro ao fazer o upload do arquivo."));
        }
    }

    @PostMapping("/upload3") //upload da imagem em /resources/static
    public ResponseEntity<String> uploadFileToResources(@RequestParam("file") MultipartFile file) {
        try {
            file.transferTo(RESOURCES.resolve(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()))).normalize());
            return ResponseEntity.ok(String.format("%s", "Upload realizado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(String.format("%s", "Deu erro ao fazer o upload do arquivo."));
        }
    }
}