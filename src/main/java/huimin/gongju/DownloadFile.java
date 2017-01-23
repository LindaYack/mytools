package huimin.gongju;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFile {

	public static void main(String[] args) {
		String prefix="http://music1.wemark.cn/pic/newyear/small/m_";
		String suffix=".jpg";
		String localprefix="D:/phpStudy/WWW/wap/public/static/card/backgroundimages/newyear/small/m_";
		String localsuffix=".jpg";
		for(int i=1,j=1;i<=95;i++){
			try{
				DownloadFile.downloadFile(prefix+i+suffix, localprefix+j+localsuffix);
				j++;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**  
     * 下载远程文件并保存到本地  
     * @param remoteFilePath 远程文件路径   
     * @param localFilePath 本地文件路径  
     */
    public static void downloadFile(String remoteFilePath, String localFilePath)
    {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try
        {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
