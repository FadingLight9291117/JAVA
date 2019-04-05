package tools;

import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class MailClientRecv {
	private Session session;
	private Store store;
	private String username = "fadinglight9291117@126.com";
	private String password = "m4a1421";
	private String popServer = "pop.126.com";

//	private String username = "1364409394@qq.com";
//	private String password = "pyvckwpynhykfdjh";
//	private String popServer = "pop.qq.com";

	public void init() throws Exception {
		// 设置属性
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
		props.put("mail.pop3.class", "com.sun.mail.pop3.POP3Store");

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
	//	props.setProperty("mail.pop3.socketFactory.fallback", "false");
	//	props.setProperty("mail.pop3.port", "995");
	//	props.setProperty("mail.pop3.socketFactory.port", "995");
	//	props.setProperty("mail.pop3.auth", "true");

		// 创建Session对象
		session = Session.getInstance(props, null);
		session.setDebug(false); // 输出跟踪日志

		// 创建Store对象
		store = session.getStore("pop3");
		// 连接到收邮件服务器
		store.connect(popServer, username, password);
		System.out.println("pop服务器连接成功");
	}

	public void receiveMessage() throws Exception {
		String folderName = "inbox";
		Folder folder = store.getFolder(folderName);
		if (folder == null) {
			throw new Exception(folderName + "邮件夹不存在");
		}
		// 打开信箱
		folder.open(Folder.READ_ONLY);
		System.out.println("您的收件箱有" + folder.getMessageCount() + "封邮件.");
		System.out.println("您的收件箱有" + folder.getUnreadMessageCount() + "封未读的邮件.");

		// 读邮件
		Message[] messages = folder.getMessages();
		for (int i = 0; i < messages.length; i++)
		// for(int i=1;i<=3;i++)
		{
			System.out.println("------第" + (i + 1) + "封邮件-------");
			// 打印邮件信息
			Message message = messages[i];
			// folder.getMessage(i).writeTo(System.out);
			System.out.println((message.getFrom())[0]);
			System.out.println(message.getSubject());
			System.out.println(message.getSentDate());
			System.out.println(message.getContent());
			System.out.println();
		}
		folder.close(false); // 关闭邮件夹
	}

	public void close() throws Exception {
		store.close();
	}

	public static void main(String[] args) throws Exception {
		MailClientRecv client = new MailClientRecv();
		// 初始化
		client.init();
		// 接收邮件
		client.receiveMessage();
		// 关闭连接
		client.close();
	}
}
