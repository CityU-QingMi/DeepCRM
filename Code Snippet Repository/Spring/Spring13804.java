	@Override
	protected ListenableFuture<WebSocketSession> doHandshakeInternal(WebSocketHandler webSocketHandler,
			HttpHeaders headers, final URI uri, List<String> protocols,
			List<WebSocketExtension> extensions, Map<String, Object> attributes) {

		int port = getPort(uri);
		InetSocketAddress localAddress = new InetSocketAddress(getLocalHost(), port);
		InetSocketAddress remoteAddress = new InetSocketAddress(uri.getHost(), port);

		final StandardWebSocketSession session = new StandardWebSocketSession(headers,
				attributes, localAddress, remoteAddress);

		final ClientEndpointConfig endpointConfig = ClientEndpointConfig.Builder.create()
				.configurator(new StandardWebSocketClientConfigurator(headers))
				.preferredSubprotocols(protocols)
				.extensions(adaptExtensions(extensions)).build();

		endpointConfig.getUserProperties().putAll(getUserProperties());

		final Endpoint endpoint = new StandardWebSocketHandlerAdapter(webSocketHandler, session);

		Callable<WebSocketSession> connectTask = () -> {
			webSocketContainer.connectToServer(endpoint, endpointConfig, uri);
			return session;
		};

		if (this.taskExecutor != null) {
			return this.taskExecutor.submitListenable(connectTask);
		}
		else {
			ListenableFutureTask<WebSocketSession> task = new ListenableFutureTask<>(connectTask);
			task.run();
			return task;
		}
	}
