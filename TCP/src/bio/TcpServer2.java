package bio;
import java.net.*;

public class TcpServer2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	
			ServerSocket serverSocket=new ServerSocket(8001);
			while(true)
			{
				Socket socket = serverSocket.accept();
				System.out.println("来了一个client");
				new Thread(new Worker(socket)).start();
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
