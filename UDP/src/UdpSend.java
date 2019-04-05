


import java.net.*;

public class UdpSend {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DatagramSocket dSocket=new DatagramSocket();
		String str="hello world";
		DatagramPacket dPacket=new DatagramPacket(str.getBytes(), str.length(),
				InetAddress.getByName("127.0.0.1"),3000);
		
		System.out.println("UdpSend:我要发送信息");
		dSocket.send(dPacket);
		System.out.println("UdpSend:我发送信息结束");
		
		Thread.sleep(1000);
		byte [] buf=new byte[1024];
		DatagramPacket dPacket2=new DatagramPacket(buf, 1024);
		System.out.println("UdpSend:我在等待消息");
		dSocket.receive(dPacket2);
		System.out.println("UdpSend:我接受消息");
		String str2=new String(dPacket2.getData(),0,dPacket2.getLength())+
				" from"+dPacket2.getAddress().getHostAddress()+":"+dPacket2.getAddress();
		
		System.out.println(str2);
		dSocket.close();
		}
}
