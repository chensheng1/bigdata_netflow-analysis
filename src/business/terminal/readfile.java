package business.terminal;

import java.io.File;
import java.text.DecimalFormat;
import java.io.FileInputStream;
public class readfile{
	public long getFileSizes(File f) throws Exception{//ȡ���ļ���С
	       long s=0;
	       if (f.exists()) {
	           FileInputStream fis = null;
	           fis = new FileInputStream(f);
	          s= fis.available();
	       } else {
	           f.createNewFile();
	           System.out.println("�ļ�������");
	       }
	       return s;
	    }
    // �ݹ�
	public long getFileSize(File f)throws Exception//ȡ���ļ��д�С
    {
       long size = 0;
       File flist[] = f.listFiles();
       for (int i = 0; i < flist.length; i++)
       {
           if (flist[i].isDirectory())
           {
               size = size + getFileSize(flist[i]);
           } else
           {
               size = size + flist[i].length();
           }
       }
       return size;
    }

    public String FormetFileSize(long fileS) {//ת���ļ���С
       DecimalFormat df = new DecimalFormat("#.00");
       String fileSizeString = "";
       fileSizeString = df.format((double) fileS/1024);
       //�ж��ļ���Сȡʲô���ĵ�λ
       /*if (fileS < 1024) {
           fileSizeString = df.format((double) fileS)+"B";
       } else if (fileS < 1048576) {
           fileSizeString = df.format((double) fileS / 1024)+"K";
       } else if (fileS < 1073741824) {
           fileSizeString = df.format((double) fileS / 1048576)+"M";
       } else {
           fileSizeString = df.format((double) fileS / 1073741824)+"G";
       }*/
       return fileSizeString;
    }

    public String test(){
    	readfile g = new readfile();
        while(true){
 	       try
 	       {
 	           long l = 0;
 	           String path = "F:\\data\\2016-06-20";
 	           File ff = new File(path);
 	           if (ff.isDirectory()) { //���·�����ļ��е�ʱ��
 	               l = g.getFileSize(ff);
 	               s= g.FormetFileSize(l); 
 	               return s;
 	           }
 	       } catch (Exception e)
 	       {
 	           e.printStackTrace();
 	       }
 	      
        }
    }
    
    
    public String s ="";
    public static void main(String args[]) throws InterruptedException
    {
    	
    	readfile cs=new readfile();
    	System.out.println(cs.test());
	}
}