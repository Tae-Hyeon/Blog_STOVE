package kr.co.fortice.blog.global.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringUtil {
    public static String parseNoImageText(String contents) {
        return contents.replaceAll("!\\[[^\\(\\)]*\\]\\(/images/[0-9]*-[0-9]*\\.png\\)", "");
    }

    public static String parseSummary(String contents) {
        String noImageText = parseNoImageText(contents).replaceAll("<br>", "\n");
        return noImageText.length() > 100 ? noImageText.substring(0, 100) : noImageText;
    };

    public static String getFirstImage(String contents) {
        Pattern pattern = Pattern.compile("!\\[[^\\(\\)]*\\]\\(/images/[0-9]*-[0-9]*\\.png\\)");
        Matcher matcher = pattern.matcher(contents);

        if(matcher.find())
        {
            String imageContent = matcher.group(0);
            int open = imageContent.indexOf('(');
            int close = imageContent.indexOf(')');
            return imageContent.substring(open + 1, close);
        }
        return "";
    }
}
