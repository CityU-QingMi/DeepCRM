	@Override
	public Mono<Void> upgrade(ServerWebExchange exchange, WebSocketHandler handler, @Nullable String subProtocol) {
		ServerHttpResponse response = exchange.getResponse();
		HttpServerResponse<?> rxNettyResponse = ((AbstractServerHttpResponse) response).getNativeResponse();

		HandshakeInfo info = getHandshakeInfo(exchange, subProtocol);
		NettyDataBufferFactory factory = (NettyDataBufferFactory) response.bufferFactory();

		WebSocketHandshaker handshaker = rxNettyResponse
				.acceptWebSocketUpgrade(conn -> {
					RxNettyWebSocketSession session = new RxNettyWebSocketSession(conn, info, factory);
					String name = HttpHandlerNames.WsServerDecoder.getName();
					session.aggregateFrames(rxNettyResponse.unsafeNettyChannel(), name);
					return RxReactiveStreams.toObservable(handler.handle(session));
				});

		if (subProtocol != null) {
			handshaker = handshaker.subprotocol(subProtocol);
		}
		else {
			// TODO: https://github.com/reactor/reactor-netty/issues/20
			handshaker = handshaker.subprotocol();
		}

		return Mono.from(RxReactiveStreams.toPublisher(handshaker));
	}
