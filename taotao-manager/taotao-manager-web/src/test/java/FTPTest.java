import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {

    @Test
    public void testFtpClient() throws Exception{
        // 创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        // 创建ftp链接，默认21端口
        ftpClient.connect("192.168.56.100", 21);
        // 登录
        ftpClient.login("ftpuser","ftpuser");
        // 上传文件
        // 读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("file:///Users\\fifi\\Desktop\\课程安排.png"));
        // 设置上传的路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        // 第一个参数：服务器端文档名
        // 第二个参数：上传文档的inputStream
        ftpClient.storeFile("hello.jpg", inputStream);
        // 关闭链接
        ftpClient.logout();
    }
}
