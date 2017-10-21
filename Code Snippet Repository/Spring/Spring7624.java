	private Message<byte[]> message(StompCommand command, String sessionId, String user, String destination) {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(command);
		if (sessionId != null) {
			accessor.setSessionId(sessionId);
		}
		if (user != null) {
			accessor.setUser(new TestPrincipal(user));
		}
		if (destination != null) {
			accessor.setDestination(destination);
		}
		accessor.setLeaveMutable(true);
		return MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());
	}
