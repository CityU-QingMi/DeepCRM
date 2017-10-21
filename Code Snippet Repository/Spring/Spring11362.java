	@Override
	public Mono<Void> upgrade(ServerWebExchange exchange, WebSocketHandler handler, @Nullable String subProtocol) {
		ServerHttpResponse response = exchange.getResponse();
		HttpServerResponse nativeResponse = ((AbstractServerHttpResponse) response).getNativeResponse();
		HandshakeInfo info = getHandshakeInfo(exchange, subProtocol);
		NettyDataBufferFactory bufferFactory = (NettyDataBufferFactory) response.bufferFactory();

		return nativeResponse.sendWebsocket(subProtocol,
				(in, out) -> handler.handle(new ReactorNettyWebSocketSession(in, out, info, bufferFactory)));
	}
