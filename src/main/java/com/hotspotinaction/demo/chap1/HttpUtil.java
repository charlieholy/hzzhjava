package com.hotspotinaction.demo.chap1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static void main(String[] args) {

        String ss = HttpUtil.openUrlTWR("http://www.baidu.com", "");
        System.out.println("a" + ss);
    }

    /**
     * ���������ڵ���Զ�̵Ľӿ�
     *
     * @param strUrl
     * @param charSet
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    public static String openUrlReturnMoreMessage(String strUrl, String charSet, int connectTimeout, int readTimeout) {
        InputStream is = null;
        String message = "";
        try {
            URL webUrl = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) webUrl.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("Connect to url[" + strUrl + "] error, response code is " + conn.getResponseCode());
            }
            is = conn.getInputStream();
            message = getReturnValueFromInputStream(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    System.err.println("openUrl(String strUrl)" + e1);
                }
            }
        }

        return message;
    }

    /**
     * ���������ڵ���Զ��HTTP�ӿ�
     *
     * @param strUrl
     * @param postParams
     * @return
     */
    public static String openUrl(String strUrl, String postParams) {
        InputStream is = null;
        OutputStream os = null;
        String message = "";
        try {
            URL webURL = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) webURL.openConnection();
            conn.setDoOutput(true);// ��д������
            conn.setDoInput(true);// �򿪶�ȡ����
            conn.setRequestMethod("POST");// �����ύ����
            conn.connect();
            os = conn.getOutputStream();
            os.write(postParams.toString().getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("response error=" + conn.getResponseCode());
            }
            is = conn.getInputStream();
            message = getReturnValueFromInputStream(is);
        } catch (Exception e) {
            // TODO �쳣����
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO �쳣����
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO �쳣����
                }
            }
        }
        return message;
    }

    /**
     * ���������ڵ���Զ��HTTP�ӿڣ� TWR�ر���Դ��
     *
     * @param strUrl
     * @param postParams
     * @return
     */
    public static String openUrlTWR(String strUrl, String postParams) {
        URL webURL = null;
        HttpURLConnection con = null;
        String message = "";

        try {
            webURL = new URL(strUrl);
            con = (HttpURLConnection) webURL.openConnection();
            con.setDoOutput(true);// ��д������
            con.setDoInput(true);// �򿪶�ȡ����
            con.setRequestMethod("GET");// �����ύ����
            con.connect();
            try (OutputStream os = con.getOutputStream(); InputStream is = con.getInputStream()) {
                os.write(postParams.toString().getBytes());
                os.flush();

                if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    System.err.println("response error=" + con.getResponseCode());
                }
                message = getReturnValueFromInputStream(is);
            }

        } catch (Exception e) {
            // TODO �쳣����
        }

        return message;
    }
    /**
     * ����������ȡ�������ݲ���ָ���ַ���ƴװ���ַ���
     *
     * @param is
     * @return
     */
    public static String getReturnValueFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            char[] temp = new char[1024];
            int charCount = 0;
            while ((charCount = br.read(temp)) != -1) {
                sb.append(new String(temp, 0, charCount));
            }
            return sb.toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
