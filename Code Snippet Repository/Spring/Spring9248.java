	@Override
	protected ListenableFuture<ClientHttpResponse> executeInternal(final HttpHeaders headers) throws IOException {
		final SettableListenableFuture<ClientHttpResponse> responseFuture = new SettableListenableFuture<>();

		ChannelFutureListener connectionListener = new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					Channel channel = future.channel();
					channel.pipeline().addLast(new RequestExecuteHandler(responseFuture));
					FullHttpRequest nettyRequest = createFullHttpRequest(headers);
					channel.writeAndFlush(nettyRequest);
				}
				else {
					responseFuture.setException(future.cause());
				}
			}
		};

		this.bootstrap.connect(this.uri.getHost(), getPort(this.uri)).addListener(connectionListener);
		return responseFuture;
	}
