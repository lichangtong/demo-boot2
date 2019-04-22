package com.example.demo.demoboot2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShortenUrl {

    private static final boolean DEBUG = true;

    public static void main(String[] args) {
        String url = "http://www.henshiyong.com/tools/sina-shorten-url.php";
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("url", "http://www.google.com");
        params.put("submit", "转换");

        String data = null;

        try {
            data = postUrl(url, params);
            if (DEBUG) {
                System.out.println(data);
            }
        } catch (IOException ex) {
        }

        if (data != null) {
            String shortUrl = getShortenUrl(data);
            if (DEBUG) {
                System.out.println(shortUrl);
            }
        }
    }

    public static String getShortenUrl(String content) {
        String url = null;
        List<String> resultList = getContext(content);

        for (Iterator<String> iterator = resultList.iterator(); iterator
                .hasNext();) {
            url = iterator.next();
        }

        return url;
    }

    /**
     * Extract "XXXX" from "<textarea>XXXX</textarea>"
     *
     * @param html
     * @return
     */
    public static List<String> getContext(String html) {
        List<String> resultList = new ArrayList<String>();
        Pattern p = Pattern.compile("<textarea>(.*)</textarea>");
        Matcher m = p.matcher(html);
        while (m.find()) {
            resultList.add(m.group(1));
        }
        return resultList;
    }

    public static class HttpException extends RuntimeException {

        private int errorCode;
        private String errorData;

        public HttpException(int errorCode, String errorData) {
            super("HTTP Code " + errorCode + " : " + errorData);
            this.errorCode = errorCode;
            this.errorData = errorData;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorData() {
            return errorData;
        }

    }

    public static String postUrl(String url, Map<String, String> params)
            throws IOException {
        String data = "";
        for (String key : params.keySet()) {
            data += "&" + URLEncoder.encode(key, "UTF-8") + "="
                    + URLEncoder.encode(params.get(key), "UTF-8");
        }
        data = data.substring(1);
        // System.out.println(data);
        URL aURL = new java.net.URL(url);
        HttpURLConnection aConnection = (java.net.HttpURLConnection) aURL
                .openConnection();
        try {
            aConnection.setDoOutput(true);
            aConnection.setDoInput(true);
            aConnection.setRequestMethod("POST");
            // aConnection.setAllowUserInteraction(false);
            // POST the data
            OutputStreamWriter streamToAuthorize = new java.io.OutputStreamWriter(
                    aConnection.getOutputStream());
            streamToAuthorize.write(data);
            streamToAuthorize.flush();
            streamToAuthorize.close();

            // check error
            int errorCode = aConnection.getResponseCode();
            if (errorCode >= 400) {
                InputStream errorStream = aConnection.getErrorStream();
                try {
                    String errorData = streamToString(errorStream);
                    throw new HttpException(errorCode, errorData);
                } finally {
                    errorStream.close();
                }
            }

            // Get the Response
            InputStream resultStream = aConnection.getInputStream();
            try {
                String responseData = streamToString(resultStream);
                return responseData;
            } finally {
                resultStream.close();
            }
        } finally {
            aConnection.disconnect();
        }
    }

    private static String streamToString(InputStream resultStream)
            throws IOException {
        BufferedReader aReader = new java.io.BufferedReader(
                new java.io.InputStreamReader(resultStream));
        StringBuffer aResponse = new StringBuffer();
        String aLine = aReader.readLine();
        while (aLine != null) {
            aResponse.append(aLine + "\n");
            aLine = aReader.readLine();
        }
        return aResponse.toString();

    }
}
