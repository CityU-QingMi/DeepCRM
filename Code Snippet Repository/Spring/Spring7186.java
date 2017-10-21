	public static SimpAttributes fromMessage(Message<?> message) {
		Assert.notNull(message, "Message must not be null");
		MessageHeaders headers = message.getHeaders();
		String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
		Map<String, Object> sessionAttributes = SimpMessageHeaderAccessor.getSessionAttributes(headers);
		if (sessionId == null) {
			throw new IllegalStateException("No session id in " + message);
		}
		if (sessionAttributes == null) {
			throw new IllegalStateException("No session attributes in " + message);
		}
		return new SimpAttributes(sessionId, sessionAttributes);
	}
