	protected AbstractWebSocketSession(T delegate, String id, HandshakeInfo handshakeInfo,
			DataBufferFactory bufferFactory) {

		Assert.notNull(delegate, "Native session is required.");
		Assert.notNull(id, "Session id is required.");
		Assert.notNull(handshakeInfo, "HandshakeInfo is required.");
		Assert.notNull(bufferFactory, "DataBuffer factory is required.");

		this.delegate = delegate;
		this.id = id;
		this.handshakeInfo = handshakeInfo;
		this.bufferFactory = bufferFactory;
	}
