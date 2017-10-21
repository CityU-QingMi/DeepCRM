	@Override
	public void afterConnected(TcpConnection<byte[]> connection) {
		this.connection = connection;
		if (logger.isDebugEnabled()) {
			logger.debug("Connection established in session id=" + this.sessionId);
		}
		StompHeaderAccessor accessor = createHeaderAccessor(StompCommand.CONNECT);
		accessor.addNativeHeaders(this.connectHeaders);
		accessor.setAcceptVersion("1.1,1.2");
		Message<byte[]> message = createMessage(accessor, EMPTY_PAYLOAD);
		execute(message);
	}
