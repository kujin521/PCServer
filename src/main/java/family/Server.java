package family;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import operator.Dir;
import Thread.Task;

public class Server {   
    
   public static void main(String args[]) throws IOException {   
      //Ϊ�˼���������е��쳣��Ϣ��������   
      int port = 5507;   
      //����һ��ServerSocket�����ڶ˿�5507��   
      
	ServerSocket server = new ServerSocket(port);  
      
      while(true)
      {
    	//server���Խ�������Socket����������server��accept����������ʽ��   
          Socket socket=server.accept();   
         
          //ÿ���յ�һ��socket�ͽ���һ���µ��߳���������
          new Thread(new Task(socket)).start();
      }
      
   }   
   
  
}  
