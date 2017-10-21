	@Override
	public final void unregisterSubscription(Message<?> message) {
		MessageHeaders headers = message.getHeaders();

		SimpMessageType messageType = SimpMessageHeaderAccessor.getMessageType(headers);
		if (!SimpMessageType.UNSUBSCRIBE.equals(messageType)) {
			throw new IllegalArgumentException("Expected UNSUBSCRIBE: " + message);
		}

		String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
		if (sessionId == null) {
			if (logger.isErrorEnabled()) {
				logger.error("No sessionId in " + message);
			}
			return;
		}

		String subscriptionId = SimpMessageHeaderAccessor.getSubscriptionId(headers);
		if (subscriptionId == null) {
			if (logger.isErrorEnabled()) {
				logger.error("No subscriptionId " + message);
			}
			return;
		}

		removeSubscriptionInternal(sessionId, subscriptionId, message);
	}
