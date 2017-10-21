	@Override
	public Mono<Void> handleRequest(ServerWebExchange exchange, WebSocketHandler handler) {
		ServerHttpRequest request = exchange.getRequest();
		HttpMethod method = request.getMethod();
		HttpHeaders headers = request.getHeaders();

		if (logger.isDebugEnabled()) {
			logger.debug("Handling " + request.getURI() + " with headers: " + headers);
		}

		if (HttpMethod.GET != method) {
			return Mono.error(new MethodNotAllowedException(
					request.getMethodValue(), Collections.singleton(HttpMethod.GET)));
		}

		if (!"WebSocket".equalsIgnoreCase(headers.getUpgrade())) {
			return handleBadRequest("Invalid 'Upgrade' header: " + headers);
		}

		List<String> connectionValue = headers.getConnection();
		if (!connectionValue.contains("Upgrade") && !connectionValue.contains("upgrade")) {
			return handleBadRequest("Invalid 'Connection' header: " + headers);
		}

		String key = headers.getFirst(SEC_WEBSOCKET_KEY);
		if (key == null) {
			return handleBadRequest("Missing \"Sec-WebSocket-Key\" header");
		}

		String protocol = selectProtocol(headers, handler);
		return this.upgradeStrategy.upgrade(exchange, handler, protocol);
	}
