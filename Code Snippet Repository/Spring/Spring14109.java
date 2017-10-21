	private Message<byte[]> createMessage(SimpMessageType type, String sessionId, String subscriptionId,
			String destination) {

		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(type);
		accessor.setSessionId(sessionId);
		if (destination != null) {
			accessor.setDestination(destination);
		}
		if (subscriptionId != null) {
			accessor.setSubscriptionId(subscriptionId);
		}
		return MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());
	}
