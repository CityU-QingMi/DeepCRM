	private Bootstrap buildBootstrap(URI uri, boolean isSecure) {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(this.eventLoopGroup).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						configureChannel(channel.config());
						ChannelPipeline pipeline = channel.pipeline();
						if (isSecure) {
							Assert.notNull(sslContext, "sslContext should not be null");
							pipeline.addLast(sslContext.newHandler(channel.alloc(), uri.getHost(), uri.getPort()));
						}
						pipeline.addLast(new HttpClientCodec());
						pipeline.addLast(new HttpObjectAggregator(maxResponseSize));
						if (readTimeout > 0) {
							pipeline.addLast(new ReadTimeoutHandler(readTimeout,
									TimeUnit.MILLISECONDS));
						}
					}
				});
		return bootstrap;
	}
