package kr.co.fortice.blog.global.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalVO {
    private static String imagePath;
    private static String imageServerURL;

    public static String getImagePath() {
        return imagePath;
    }

    public static String getImageServerURL() {
        return imageServerURL;
    }

    @Value("${server.path.image}")
    public void setImagePath(String imagePath) {
        GlobalVO.imagePath = imagePath;
    }

    @Value("${image-server.url}")
    public void setImageServerURL(String imageServerURL) {
        GlobalVO.imageServerURL = imageServerURL;
    }
}
