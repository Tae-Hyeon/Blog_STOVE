package kr.co.fortice.blog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String parseNoImageText(String contents) {
        return contents.replaceAll("\\[.*\\]\\(.*\\)", "");
    }

    public static String parseSummary(String contents) {
        return parseNoImageText(contents).substring(0, 100);
    };

    public static String getFirstImage(String contents) {
        Pattern pattern = Pattern.compile("\\[.*\\]\\(.*\\)");
        Matcher matcher = pattern.matcher(contents);
        String imageContent = matcher.group(0);
        int open = imageContent.indexOf('(');

        return imageContent.substring(open + 1, imageContent.length() - 1);
    }
}
