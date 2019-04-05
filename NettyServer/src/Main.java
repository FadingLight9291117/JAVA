
public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int port = 8001;
		DiscardServer server = new DiscardServer(port);
		server.run();
	}

}
