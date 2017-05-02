package DiceCrawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;

class DownloadTask implements Runnable {
    private String url;
    private String dir;
    // ������run�����в�����쳣��Ȼ���Զ��巽���׳��쳣
    private Throwable exception;
    //�Ƿ�رմ���������
    private boolean isStop = false;
    
    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }

    public DownloadTask(String url, String dir) {
        this.url = url;
        this.dir = dir;
    }

    /**
     * ���ش�����
     * 
     * @param filename
     * @throws FileNotFoundException
     *             , IOException
     */
    private void download() throws FileNotFoundException, IOException {
		try {
			URL httpurl = new URL(url);
			String fileName = getFileNameFromUrl(url);
			System.out.println(fileName);
			File f = new File(dir + "/" + fileName);
			FileUtils.copyURLToFile(httpurl, f);
		} catch (Exception e) {
			e.printStackTrace();

		}
		

	}
    
	public static String getFileNameFromUrl(String url) {
		String name = new Long(System.currentTimeMillis()).toString() + ".X";
		int index = url.lastIndexOf("/");
		if (index > 0) {
			name = url.substring(index + 1);
			if (name.trim().length() > 0) {
				return name;
			}
		}
		return name;
	}

    public void throwException() throws FileNotFoundException, IOException {
        if (exception instanceof FileNotFoundException)
            throw (FileNotFoundException) exception;
        if (exception instanceof IOException)
            throw (IOException) exception;
    }

    @Override
    public void run() {
        try {
            download();
        } catch (FileNotFoundException e) {
            exception = e;
        } catch (IOException e) {
            exception = e;
        }

    }
}
