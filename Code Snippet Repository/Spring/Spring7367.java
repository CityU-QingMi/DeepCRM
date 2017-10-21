	@Override
	public ListenableFuture<Void> connect(TcpConnectionHandler<P> handler, ReconnectStrategy strategy) {
		Assert.notNull(handler, "TcpConnectionHandler is required");
		Assert.notNull(strategy, "ReconnectStrategy is required");

		if (this.stopping) {
			return handleShuttingDownConnectFailure(handler);
		}

		// Report first connect to the ListenableFuture
		MonoProcessor<Void> connectMono = MonoProcessor.create();

		this.tcpClient
				.newHandler(new ReactorNettyHandler(handler))
				.doOnNext(updateConnectMono(connectMono))
				.doOnError(updateConnectMono(connectMono))
				.doOnError(handler::afterConnectFailure)    // report all connect failures to the handler
				.flatMap(NettyContext::onClose)                // post-connect issues
				.retryWhen(reconnectFunction(strategy))
				.repeatWhen(reconnectFunction(strategy))
				.subscribe();

		return new MonoToListenableFutureAdapter<>(connectMono);
	}
