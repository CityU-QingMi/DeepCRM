	private void execute(Message<byte[]> message) {
		if (logger.isTraceEnabled()) {
			StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
			if (accessor != null) {
				logger.trace("Sending " + accessor.getDetailedLogMessage(message.getPayload()));
			}
		}
		TcpConnection<byte[]> conn = this.connection;
		Assert.state(conn != null, "Connection closed");
		try {
			conn.send(message).get();
		}
		catch (ExecutionException ex) {
			throw new MessageDeliveryException(message, ex.getCause());
		}
		catch (Throwable ex) {
			throw new MessageDeliveryException(message, ex);
		}
	}
