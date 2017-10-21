	@Override
	public final MultiValueMap<String, String> findSubscriptions(Message<?> message) {
		MessageHeaders headers = message.getHeaders();

		SimpMessageType type = SimpMessageHeaderAccessor.getMessageType(headers);
		if (!SimpMessageType.MESSAGE.equals(type)) {
			throw new IllegalArgumentException("Unexpected message type: " + type);
		}

		String destination = SimpMessageHeaderAccessor.getDestination(headers);
		if (destination == null) {
			if (logger.isErrorEnabled()) {
				logger.error("No destination in " + message);
			}
			return EMPTY_MAP;
		}

		return findSubscriptionsInternal(destination, message);
	}
