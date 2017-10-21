	private MessageHeaders createHeaders(@Nullable String sessionId, String subscriptionId, MethodParameter returnType) {
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		if (getHeaderInitializer() != null) {
			getHeaderInitializer().initHeaders(accessor);
		}
		if (sessionId != null) {
			accessor.setSessionId(sessionId);
		}
		accessor.setSubscriptionId(subscriptionId);
		accessor.setHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER, returnType);
		accessor.setLeaveMutable(true);
		return accessor.getMessageHeaders();
	}
