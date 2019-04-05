
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelHandlerAdapter {
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// Discard the received data silently.
		try {
			String content = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
			System.out.println(ctx.channel().remoteAddress()+"===>>>"+content);
		} finally {
			ReferenceCountUtil.release(msg);
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
