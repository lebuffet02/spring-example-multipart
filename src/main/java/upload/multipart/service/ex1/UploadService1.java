package upload.multipart.service.ex1;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upload.multipart.dto.FileDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


@Service
public class UploadService1 {

    public FileDTO getUploadFile(MultipartFile file) throws IOException {
        if(file == null || file.isEmpty()) {
            throw new RuntimeException("Arquivo sem nome ou vazio");
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setName(file.getName());
        fileDTO.setSize(file.getSize());
        fileDTO.setBytes(file.getBytes());
        return fileDTO;
    }

    public String uploadDocument(MultipartFile file) {
        try {
            if(file.isEmpty()) {
                return "obrigatorio";
            }

            String PATH = "Users/admin/Downloads/";
            String rootFile = PATH.concat(Objects.requireNonNull(file.getOriginalFilename()));
            File newDocument = new File(rootFile);
            FileOutputStream fileOutputStream = new FileOutputStream(newDocument, true);
            file.getInputStream().transferTo(fileOutputStream);
            return file.getName();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar arquivo");
        }
    }
}
