import java.awt.Event;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new MyWebSocketChannelHander());
			
			System.out.println("服务器开启等待客户端连接...");
			Channel ch = bootstrap.bind(8001).sync().channel();
			ch.closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//优雅地退出程序
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
