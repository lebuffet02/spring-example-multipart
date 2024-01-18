//package upload.multipart.controller.ex1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import upload.multipart.dto.FileDTO;
//import upload.multipart.service.ex1.UploadService1;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("upload")
//public class UploadController1 {
//
//    @Autowired
//    UploadService1 uploadService1;
//
//    @PostMapping("/t")
//    public ResponseEntity<FileDTO> upload(
//            @RequestParam("file") MultipartFile file) throws IOException {
//        return ResponseEntity.ok().contentLength(file.getSize()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(uploadService1.getUploadFile(file));
//    }
//
////    @PostMapping
////    public ResponseEntity<String> uploadDocuments(@RequestParam("file") MultipartFile file) {
////        return ResponseEntity.ok(uploadService.uploadDocument(file));
////    }
//
//
//}
