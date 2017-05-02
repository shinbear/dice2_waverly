package DiceCrawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader {

	public static void main(String[] args) {
	}

	public static String readPdf(String URL) {

		File pdfFile = new File(URL);
		PDDocument document = null;
		try {
			// ��ʽһ��
			/**
			 * InputStream input = null; input = new FileInputStream( pdfFile );
			 * //���� pdf �ĵ� PDFParser parser = new PDFParser(new
			 * RandomAccessBuffer(input)); parser.parse(); document =
			 * parser.getPDDocument();
			 **/

			// ��ʽ����
			document = PDDocument.load(pdfFile);

			// ��ȡҳ��
			int pages = document.getNumberOfPages();

			// ���ı�����
			PDFTextStripper stripper = new PDFTextStripper();
			// ���ð�˳�����
			stripper.setSortByPosition(true);
			stripper.setStartPage(1);
			stripper.setEndPage(pages);
			String content = stripper.getText(document);
			System.out.println(content);
			return content;
		} catch (Exception e) {
			System.out.println(e);
			return "";
		} finally {
			if (document != null) {
				// �ر�PDF Document
				try {
					document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}