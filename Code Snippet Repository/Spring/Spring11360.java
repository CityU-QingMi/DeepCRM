	@Override
	public Mono<Void> upgrade(ServerWebExchange exchange, WebSocketHandler handler, @Nullable String subProtocol) {
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		HttpServletRequest servletRequest = getHttpServletRequest(request);
		HttpServletResponse servletResponse = getHttpServletResponse(response);

		JettyWebSocketHandlerAdapter adapter = new JettyWebSocketHandlerAdapter(handler,
				session -> {
					HandshakeInfo info = getHandshakeInfo(exchange, subProtocol);
					DataBufferFactory factory = response.bufferFactory();
					return new JettyWebSocketSession(session, info, factory);
				});

		startLazily(servletRequest);

		Assert.state(this.factory != null, "No WebSocketServerFactory available");
		boolean isUpgrade = this.factory.isUpgradeRequest(servletRequest, servletResponse);
		Assert.isTrue(isUpgrade, "Not a WebSocket handshake");

		try {
			adapterHolder.set(new WebSocketHandlerContainer(adapter, subProtocol));
			this.factory.acceptWebSocket(servletRequest, servletResponse);
		}
		catch (IOException ex) {
			return Mono.error(ex);
		}
		finally {
			adapterHolder.remove();
		}

		return Mono.empty();
	}
