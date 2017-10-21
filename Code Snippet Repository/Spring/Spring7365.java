	public ReactorNettyTcpClient(Consumer<ClientOptions.Builder<?>> optionsConsumer, ReactorNettyCodec<P> codec) {
		Assert.notNull(optionsConsumer, "Consumer<ClientOptions.Builder<?> is required");
		Assert.notNull(codec, "ReactorNettyCodec is required");

		this.channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
		this.loopResources = LoopResources.create("reactor-netty-tcp-client");
		this.poolResources = PoolResources.fixed("reactor-netty-tcp-pool");

		Consumer<ClientOptions.Builder<?>> builtInConsumer = opts -> opts
				.channelGroup(this.channelGroup)
				.loopResources(this.loopResources)
				.poolResources(this.poolResources)
				.preferNative(false);

		this.tcpClient = TcpClient.create(optionsConsumer.andThen(builtInConsumer));
		this.codec = codec;
	}
