package DiceCrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LoadSomething {
    // �̳߳ط���ӿ�
    private ExecutorService executor;

    
    public LoadSomething() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void beginToLoad(DownloadTask task, long timeout,
            TimeUnit timeType) {
        Future<?> future = executor.submit(task);
        try {
            future.get(timeout, timeType);
            task.throwException();
        } catch (InterruptedException e) {
            System.out.println("���������Ѿ�ȡ��");
        } catch (ExecutionException e) {
            System.out.println("�����з�����������������");
        } catch (TimeoutException e) {
            System.out.println("���س�ʱ����������ص�");
        } catch (FileNotFoundException e) {
            System.out.println("������Դ�Ҳ���");
        } catch (IOException e) {
            System.out.println("����������");
        } finally {
            task.setStop(true);
           
            // ��Ϊ��������ز��Բ��õõ����ؽ����ȡ�����񲻻�Ӱ����
            future.cancel(true);         
        }
    }
}