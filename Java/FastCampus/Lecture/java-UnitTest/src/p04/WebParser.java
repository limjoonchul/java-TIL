package com.company.p04;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebParser {
    private Http http;

    // dependency injection di를하기위해 리팩토링을함
    // 테스트 케이스로 인해서 더 테스트하기  좋은 코드로 수정하였다. 이부분이 중요함!!!!
    public WebParser(Http http) {
        this.http = http;
    }

    public int countImageFromWebPage(String url) throws IOException {
        String text = http.get(url);
//        String text = new HttpImpl.get(url);

        Pattern pattern = Pattern.compile("(\\w+.(png|jpg|gif))");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()){
            count++;
        }

        System.out.println(text);
        return count;
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println(new WebParser().countImageFromWebPage("http://google.com"));
//    }
}
