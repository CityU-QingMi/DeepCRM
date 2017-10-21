	@Override
	public final void registerSubscription(Message<?> message) {
		MessageHeaders headers = message.getHeaders();

		SimpMessageType messageType = SimpMessageHeaderAccessor.getMessageType(headers);
		if (!SimpMessageType.SUBSCRIBE.equals(messageType)) {
			throw new IllegalArgumentException("Expected SUBSCRIBE: " + message);
		}

		String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
		if (sessionId == null) {
			if (logger.isErrorEnabled()) {
				logger.error("No sessionId in  " + message);
			}
			return;
		}

		String subscriptionId = SimpMessageHeaderAccessor.getSubscriptionId(headers);
		if (subscriptionId == null) {
			if (logger.isErrorEnabled()) {
				logger.error("No subscriptionId in " + message);
			}
			return;
		}

		String destination = SimpMessageHeaderAccessor.getDestination(headers);
		if (destination == null) {
			if (logger.isErrorEnabled()) {
				logger.error("No destination in " + message);
			}
			return;
		}

		addSubscriptionInternal(sessionId, subscriptionId, destination, message);
	}
