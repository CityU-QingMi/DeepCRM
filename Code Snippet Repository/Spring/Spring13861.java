	private StompHeaderAccessor convertConnectAcktoStompConnected(StompHeaderAccessor connectAckHeaders) {
		String name = StompHeaderAccessor.CONNECT_MESSAGE_HEADER;
		Message<?> message = (Message<?>) connectAckHeaders.getHeader(name);
		if (message == null) {
			throw new IllegalStateException("Original STOMP CONNECT not found in " + connectAckHeaders);
		}

		StompHeaderAccessor connectHeaders = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		StompHeaderAccessor connectedHeaders = StompHeaderAccessor.create(StompCommand.CONNECTED);

		if (connectHeaders != null) {
			Set<String> acceptVersions = connectHeaders.getAcceptVersion();
			if (acceptVersions.contains("1.2")) {
				connectedHeaders.setVersion("1.2");
			}
			else if (acceptVersions.contains("1.1")) {
				connectedHeaders.setVersion("1.1");
			}
			else if (!acceptVersions.isEmpty()) {
				throw new IllegalArgumentException("Unsupported STOMP version '" + acceptVersions + "'");
			}
		}

		long[] heartbeat = (long[]) connectAckHeaders.getHeader(SimpMessageHeaderAccessor.HEART_BEAT_HEADER);
		if (heartbeat != null) {
			connectedHeaders.setHeartbeat(heartbeat[0], heartbeat[1]);
		}
		else {
			connectedHeaders.setHeartbeat(0, 0);
		}

		return connectedHeaders;
	}
