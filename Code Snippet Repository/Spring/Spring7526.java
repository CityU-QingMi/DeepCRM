	private Message<?> subscribeMessage(String sessionId, String subscriptionId, String dest, String selector) {
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(SimpMessageType.SUBSCRIBE);
		accessor.setSessionId(sessionId);
		accessor.setSubscriptionId(subscriptionId);
		if (dest != null) {
			accessor.setDestination(dest);
		}
		if (selector != null) {
			accessor.setNativeHeader("selector", selector);
		}
		return MessageBuilder.createMessage("", accessor.getMessageHeaders());
	}
