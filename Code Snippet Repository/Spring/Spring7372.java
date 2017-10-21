	@Override
	@SuppressWarnings("")
	public void onReadInactivity(Runnable runnable, long inactivityDuration) {
		// TODO: workaround for https://github.com/reactor/reactor-netty/issues/22
		ChannelPipeline pipeline = this.inbound.context().channel().pipeline();
		String name = NettyPipeline.OnChannelReadIdle;
		if (pipeline.context(name) != null) {
			pipeline.remove(name);
		}

		this.inbound.onReadIdle(inactivityDuration, runnable);
	}
