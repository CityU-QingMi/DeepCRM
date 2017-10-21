	private Message<byte[]> createDisconnectMessage(WebSocketSession session) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.create(StompCommand.DISCONNECT);
		if (getHeaderInitializer() != null) {
			getHeaderInitializer().initHeaders(headerAccessor);
		}

		headerAccessor.setSessionId(session.getId());
		headerAccessor.setSessionAttributes(session.getAttributes());

		Principal user = getUser(session);
		if (user != null) {
			headerAccessor.setUser(user);
		}

		return MessageBuilder.createMessage(EMPTY_PAYLOAD, headerAccessor.getMessageHeaders());
	}
