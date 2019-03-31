package Thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import operator.Dir;
import operator.Downland;
import operator.Open;
import operator.Update;
import operator.Down_duan;

public class Task implements Runnable{

	private Socket socket;
	   
	   public Task(Socket socket){
		   this.socket=socket;
	   }
	   
	   public void run()
	   {
		   try{
		   handleSocket();
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }

	   //�Ϳͻ���ͨ��
       private void handleSocket() throws Exception{
	   //���ͻ��˽���������֮�����ǾͿ��Ի�ȡsocket��InputStream�������ж�ȡ�ͻ��˷���������Ϣ�ˡ�   
          Reader reader = new InputStreamReader(socket.getInputStream(),"utf-8");   
          char chars[] = new char[64];   
          int len=-1;   
          StringBuilder sb = new StringBuilder();   
          
          String temp;
          int index;
          String type = null;
          
          while ((len=reader.read(chars)) != -1) {  
        	  temp=new String(chars,0,len);
        	  type=temp.substring(0, 1);
    		  System.out.println(type);
    		  if(type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4")||type.equals("5")||type.equals("8"))
    		  {
        	    if((index=temp.indexOf("eof"))!=-1)  //����eof�ͽ���
        	    {
        		    sb.append(temp.substring(1, index));
   
        		  break;
        	    }
        	    sb.append(temp);
    		  }
    		  
    		}
         
          String str=sb.toString();   //�ͻ��˷���������Ϣ
          
          System.out.println(type);
          System.out.println(str);
          
          if(type.equals("1"))  //����
          {
            String str1=Dir.exe(str);	   
            Writer writer = new OutputStreamWriter(socket.getOutputStream(),"utf-8");  
            writer.write(str1);  
            writer.flush();  
            writer.close();   
            reader.close();   
            socket.close();
          }
          
          else if(type.equals("2"))  //����
          {
        	  
        	 Downland.down(str,socket);    //ִ����������
        	 socket.close();
          }
          
          else if(type.equals("3"))  //�ϴ�
		  {
        	  Update.update(str, socket);
	         //   Socket socket2=new ServerSocket(5507).accept();
	             
		  }
          else if(type.equals("4"))  //�ϵ�����
          {
        	  File file = new File(str);
      		  Long len_str4 = file.length();
      		  System.out.println(len_str4);
              Writer writer = new OutputStreamWriter(socket.getOutputStream(),"utf-8");  
              writer.write(String.valueOf(len_str4));   //��Ҫ���ص��ļ���С����ȥ
              writer.flush();  
              writer.close();   
              reader.close();   
              socket.close();
          }
          
          else if(type.equals("5"))   //��ʼ�ϵ�����
          {
        	  Down_duan.down(str,socket);    //ִ�п�ʼ�ϵ���������
          }
          //writer.write("eof\n");
       
		      
          else if(type.equals("8"))   //Զ�̴�
          {
        	  Open.open(str);
        	  socket.close();
          }
          
          else if(type.equals("9"))   //�ڷ������˴������
          {
        	  Open.openBrow();
        	  socket.close();
          }
          
	}
}