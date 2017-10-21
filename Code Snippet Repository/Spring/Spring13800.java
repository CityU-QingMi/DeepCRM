	@Override
	public ListenableFuture<WebSocketSession> doHandshakeInternal(WebSocketHandler wsHandler,
			HttpHeaders headers, final URI uri, List<String> protocols,
			List<WebSocketExtension> extensions,  Map<String, Object> attributes) {

		final ClientUpgradeRequest request = new ClientUpgradeRequest();
		request.setSubProtocols(protocols);

		for (WebSocketExtension e : extensions) {
			request.addExtensions(new WebSocketToJettyExtensionConfigAdapter(e));
		}

		for (String header : headers.keySet()) {
			request.setHeader(header, headers.get(header));
		}

		Principal user = getUser();
		final JettyWebSocketSession wsSession = new JettyWebSocketSession(attributes, user);
		final JettyWebSocketHandlerAdapter listener = new JettyWebSocketHandlerAdapter(wsHandler, wsSession);

		Callable<WebSocketSession> connectTask = () -> {
			Future<Session> future = client.connect(listener, uri, request);
			future.get();
			return wsSession;
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
