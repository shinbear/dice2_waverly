package DiceCrawler;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.*;

public class loginlinkedin {
	public static void main(String[] args) throws IOException {
		WebClient webClient = new WebClient();// ����WebClient
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		// ��ȡҳ��
		HtmlPage page = webClient.getPage("https://www.linkedin.com/uas/login"); // ��linkedin

		// ���nameΪ"session_key"��htmlԪ��
		HtmlElement usernameEle = page.getElementByName("session_key");
		// ���idΪ"session_password"��htmlԪ��
		HtmlElement passwordEle = (HtmlElement) page.getElementById("session_password-login");
		usernameEle.focus(); // �������뽹��
		usernameEle.type("z_hao1975@hotmail.com"); // ��дֵ

		passwordEle.focus(); // �������뽹��
		passwordEle.type("zh197544"); // ��дֵ
		// ���nameΪ"submit"��Ԫ��
		HtmlElement submitEle = page.getElementByName("signin");
		// �������½��
		page = submitEle.click();
		String result = page.asXml();// ���click()���htmlҳ�棨������ǩ��
		if (result.contains("Sign Out")) {
			System.out.println("��½�ɹ�");
		} else {
			System.out.println("��½ʧ��");
		}

	}

}
