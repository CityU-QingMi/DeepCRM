	@Override
	public Mono<Void> execute(URI url, HttpHeaders headers, WebSocketHandler handler) {
		List<String> protocols = beforeHandshake(url, headers, handler);

		return getHttpClient()
				.ws(url.toString(),
						nettyHeaders -> setNettyHeaders(headers, nettyHeaders),
						StringUtils.collectionToCommaDelimitedString(protocols))
				.flatMap(response -> {
					HandshakeInfo info = afterHandshake(url, toHttpHeaders(response));
					ByteBufAllocator allocator = response.channel().alloc();
					NettyDataBufferFactory factory = new NettyDataBufferFactory(allocator);
					return response.receiveWebsocket((in, out) -> {
						WebSocketSession session = new ReactorNettyWebSocketSession(in, out, info, factory);
						return handler.handle(session);
					});
				});
	}
