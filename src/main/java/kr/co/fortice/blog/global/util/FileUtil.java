package kr.co.fortice.blog.global.util;

import kr.co.fortice.blog.global.common.GlobalVO;
import kr.co.fortice.blog.global.exception.custom.MultipartFileTypeRestrictException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class FileUtil {
    private static Boolean checkFileType(MultipartFile file) {
        String mimeType = file.getContentType();
        if (mimeType == null || !mimeType.split("/")[0].equals("image"))
            throw new MultipartFileTypeRestrictException();

        return true;
    }

    public static String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty())
            return "";
        checkFileType(file);

        String filename = file.getOriginalFilename().replaceAll(" ", "");
        filename = Long.toString(new Date().getTime()) + "-" + Integer.toString((int)(Math.random() * 100));//URLEncoder.encode(filename, StandardCharsets.UTF_8);
        File dest = new File(GlobalVO.getImagePath() + filename);
        file.transferTo(dest);
        return GlobalVO.getImageServerURL() + filename;
    }

    public static void deleteFile(String path) {
        File deleteFile = new File(GlobalVO.getImagePath() + path);

        if (deleteFile.exists() && deleteFile.isFile()) {
            if (deleteFile.delete())
                System.out.println(deleteFile.getPath() + "사진 삭제 성공");
            else {
                //TODO ERROR LOG
                System.out.println(deleteFile.getPath() + "사진 삭제 실패");
            }
        }
    }
}
