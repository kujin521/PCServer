package operator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dir {

	public static String exe(String str) {
		// ���ܿͻ��˷������ļ���Ŀ¼��Ϣ

		String path = str; // �ļ���·��
		String str1 = "";
		int len_str;
		int index=-1;
		String str3 = "";

		// ������ʱ��

		if (path.equals("�ҵĵ���")) // ��ȡϵͳ��Ŀ¼
		{
			File[] root = File.listRoots();
			for (File f : root) {
				System.out.println(f + "[menu]");

				// ����ʱ��
				long lastModified = f.lastModified();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String fileDate = dateFormat.format(new Date(lastModified));

				str1 = str1 + f + "[menu]";
				str1 = str1 + fileDate + "[date]" + "[size]" + "\n\r";

			}

			// ����"/"����
			len_str = str1.length();
			while (len_str > 0) {
				if ((index = str1.indexOf("[menu]")) != -1) {
					String out_string = str1.substring(0, index - 1) + "[menu]";
					str1 = str1.substring(index + 6, len_str);
					len_str = len_str - index - 6;
					str3 = str3 + out_string;
				} else
					break;
			}

			str1 = str3 + str1;
		} 
		
		
		else {  //��ȡ�����ļ�·��
			File file = new File(path);

			if (!file.exists()) {
				System.out.printf(path + " not exists");
				str1 = "notexists";
			} else {
				File files[] = file.listFiles();

				System.out.println(files.length);
				for (int i = 0; i < files.length; i++) {
					File fs = files[i];
					
					//�ļ���
					if (fs.isDirectory()) {

						System.out.println(fs.getName() + "[menu]");
						str1 = str1 + fs.getName() + "[menu]";
						// ����ʱ��
						long lastModified = fs.lastModified();
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String fileDate = dateFormat.format(new Date(
								lastModified));

						str1 = str1 + fileDate + "[date]" + "[size]" + "\n\r";

					} 
					
				
					else {   //�ļ�
						System.out.println(fs.getName());
						str1 = str1 + fs.getName() + "[file]";
						// ����ʱ��
						long lastModified = fs.lastModified();
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String fileDate = dateFormat.format(new Date(
								lastModified));

						// �ļ���С
						String fileSize = "0";
						fileSize = "" + fs.length();

						str1 = str1 + fileDate + "[date]" + fileSize + "[size]"
								+ "\n\r";
					}
				}

			}
		}

		System.out.println(str1);
		return str1;
	}

}
