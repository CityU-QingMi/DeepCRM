	@Nullable
	private ParseResult parse(Message<?> message) {
		MessageHeaders headers = message.getHeaders();
		String sourceDestination = SimpMessageHeaderAccessor.getDestination(headers);
		if (sourceDestination == null || !checkDestination(sourceDestination, this.prefix)) {
			return null;
		}
		SimpMessageType messageType = SimpMessageHeaderAccessor.getMessageType(headers);
		if (messageType != null) {
			switch (messageType) {
				case SUBSCRIBE:
				case UNSUBSCRIBE:
					return parseSubscriptionMessage(message, sourceDestination);
				case MESSAGE:
					return parseMessage(headers, sourceDestination);
			}
		}
		return null;
	}
