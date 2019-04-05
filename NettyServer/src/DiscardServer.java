import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
	private int port;

	public DiscardServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)// 指定channel使用NIO传输
					.option(ChannelOption.SO_BACKLOG,1024)
					.option(ChannelOption.SO_SNDBUF, 1024)
					.option(ChannelOption.SO_RCVBUF, 1024)
					.localAddress(new InetSocketAddress(port))// 执行端口设置套接字地址
					.childHandler(new ChannelInitializer<SocketChannel>() {
						// Channel的channelpipeline
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							ChannelPipeline channelPipeline = socketChannel.pipeline();
							channelPipeline.addFirst(new DiscardServerHandler());
						}
					});
			ChannelFuture f = serverBootstrap.bind().sync(); // 开启监听
			if (f.isSuccess()) {
				System.out.println("服务器开启等待客户端连接...");
			}

			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully().sync();
			workGroup.shutdownGracefully().sync();
		}
	}
}
