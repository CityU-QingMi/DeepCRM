	@SuppressWarnings("")
	private Observable<Void> executeInternal(URI url, HttpHeaders headers, WebSocketHandler handler) {
		List<String> protocols = beforeHandshake(url, headers, handler);
		return createRequest(url, headers, protocols)
				.flatMap(response -> {
					Observable<WebSocketConnection> conn = response.getWebSocketConnection();
					// following cast is necessary to enable compilation on Eclipse 4.6
					return (Observable<Tuple2<WebSocketResponse<ByteBuf>, WebSocketConnection>>)
							Observable.zip(Observable.just(response), conn, Tuples::of);
				})
				.flatMap(tuple -> {
					WebSocketResponse<ByteBuf> response = tuple.getT1();
					WebSocketConnection conn = tuple.getT2();

					HandshakeInfo info = afterHandshake(url, toHttpHeaders(response));
					ByteBufAllocator allocator = response.unsafeNettyChannel().alloc();
					NettyDataBufferFactory factory = new NettyDataBufferFactory(allocator);
					RxNettyWebSocketSession session = new RxNettyWebSocketSession(conn, info, factory);
					session.aggregateFrames(response.unsafeNettyChannel(), HttpHandlerNames.WsClientDecoder.getName());

					return RxReactiveStreams.toObservable(handler.handle(session));
				});
	}
