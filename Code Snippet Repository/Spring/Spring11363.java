	@Override
	public Mono<Void> upgrade(ServerWebExchange exchange, WebSocketHandler handler, @Nullable String subProtocol){
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		HttpServletRequest servletRequest = getHttpServletRequest(request);
		HttpServletResponse servletResponse = getHttpServletResponse(response);

		Endpoint endpoint = new StandardWebSocketHandlerAdapter(handler,
				session -> {
					HandshakeInfo info = getHandshakeInfo(exchange, subProtocol);
					DataBufferFactory factory = response.bufferFactory();
					return new TomcatWebSocketSession(session, info, factory);
				});

		String requestURI = servletRequest.getRequestURI();
		DefaultServerEndpointConfig config = new DefaultServerEndpointConfig(requestURI, endpoint);
		config.setSubprotocols(subProtocol != null ? Collections.singletonList(subProtocol) : Collections.emptyList());

		try {
			WsServerContainer container = getContainer(servletRequest);
			container.doUpgrade(servletRequest, servletResponse, config, Collections.emptyMap());
		}
		catch (ServletException | IOException ex) {
			return Mono.error(ex);
		}

		return Mono.empty();
	}
