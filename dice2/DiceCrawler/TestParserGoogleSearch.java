package DiceCrawler;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
 
/**
 * 
 * @author Michael
 * 
 */
public class TestParserGoogleSearch {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        TestParserGoogleSearch parser = new TestParserGoogleSearch();
        String url = "https://www.google.com/search?q=waverly";
        
        String html = parser.getUrlHtmlByHttpClient(url);
        
		ReadWriteFile.readTxtFile();
		try {
			ReadWriteFile.writeTxtFile(html);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        parser.parseHtmlLink(html);
    }
 
    /**
     * ������������ַ���
     * @param htmlstr
     */
    private void parseHtmlLink(String htmlstr) {
        try {
            Parser parser = Parser.createParser(htmlstr, "default");
            // ����TagNameFilterʵ��
            TagNameFilter filter = new TagNameFilter("A");
            // ɸѡ������A��ǩ�ڵ�
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if (nodes != null) {
                System.out.println(nodes.size());
                for (int i = 0; i < nodes.size(); i++) {
                    LinkTag tag = (LinkTag) nodes.elementAt(i);
                    System.out.println(tag.getLinkText() + " -- "
                            + tag.getLink());
                }
 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * ģ��ͻ��˷��ʻ�ȡ�������ҳ��
     * @param url
     * @return
     */
    private String getUrlHtmlByHttpClient(String url) {
        String searchHtml = null;
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
                5000);
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            InputStream bodyIs = getMethod.getResponseBodyAsStream();//
            System.out.println("get reoponse body stream:" + bodyIs);
 
            //����������� �޸��ַ���
            // BufferedReader br = new BufferedReader(
            // new InputStreamReader(bodyIs,"GBK"));
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(bodyIs));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            searchHtml = sb.toString();
            return searchHtml;
        } catch (HttpException e) {
            System.out.println("Please check your http address!");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            getMethod.releaseConnection();
        }
 
    }
 
}