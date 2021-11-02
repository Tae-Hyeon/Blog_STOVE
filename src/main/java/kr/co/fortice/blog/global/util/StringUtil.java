package kr.co.fortice.blog.global.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringUtil {
    public static String parseNoImageText(String contents) {
        return contents.replaceAll("\\[.*\\]\\(.*\\)", "");
    }

    public static String parseSummary(String contents) {
        return contents.length() > 100 ? parseNoImageText(contents).substring(0, 100) : contents;
    };

    public static String getFirstImage(String contents) {
        Pattern pattern = Pattern.compile("\\[.*\\]\\(.*\\)");
        Matcher matcher = pattern.matcher(contents);
        if(matcher.find())
        {
            String imageContent = matcher.group(0);
            int open = imageContent.indexOf('(');
            return imageContent.substring(open + 1, imageContent.length() - 1);
        }
        return "";
    }
}
