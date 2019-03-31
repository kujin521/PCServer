package operator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Down_duan {

	public static void down(String str, Socket socket1) throws EOFException {
		Socket socket = socket1;
		Long size = null;
		
		// ���ܿͻ��˷������������ļ���Ϣ
		
		int in=-1;
        String path = null;
        String down_size;
        if ((in = str.indexOf("[Path]")) != -1) {

            path= str.substring(0, in);  //�ϵ����ص��ļ�·��
            System.out.println(path);
            final int len_str_down=str.length();
            down_size = str.substring(in + 6, len_str_down);  
            size=Long.valueOf(down_size);  //�ϵ����ص��ļ��ϴδ����λ��
        }

		// ������ʱ��

		try {

			File file = null;
			FileInputStream fi = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;

			file = new File(path);
			fi = new FileInputStream(file);
			fi.skip(size);  //��ĳ��λ�ÿ�ʼ��
			bis = new BufferedInputStream(fi);
			dis = new DataInputStream(bis);

			BufferedOutputStream fos = null;
			DataOutputStream dos = null;
			fos = new BufferedOutputStream(socket.getOutputStream());
			dos = new DataOutputStream(fos);

			byte[] by = new byte[1024];
			int len = -1; // ���ֽڶ�ȡʣ�������
			Long ind=Long.valueOf(0);
			
			while((len=dis.read(by))!=-1)
			{
				 
				System.out.println(len);
				ind+=len;
				System.out.println(ind);
				dos.write(by, 0, len);
				  
			}	
			dos.flush();
			dos.close();
			fos.close();
	        fi.close();
		    bis.close();
			dis.close();
				
	  		socket.close();
	         
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
