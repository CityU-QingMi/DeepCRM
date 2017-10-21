	@Override
	protected void doSend(String destination, Message<?> message) {
		Assert.notNull(destination, "Destination must not be null");

		SimpMessageHeaderAccessor simpAccessor =
				MessageHeaderAccessor.getAccessor(message, SimpMessageHeaderAccessor.class);

		if (simpAccessor != null) {
			if (simpAccessor.isMutable()) {
				simpAccessor.setDestination(destination);
				simpAccessor.setMessageTypeIfNotSet(SimpMessageType.MESSAGE);
				simpAccessor.setImmutable();
				sendInternal(message);
				return;
			}
			else {
				// Try and keep the original accessor type
				simpAccessor = (SimpMessageHeaderAccessor) MessageHeaderAccessor.getMutableAccessor(message);
				initHeaders(simpAccessor);
			}
		}
		else {
			simpAccessor = SimpMessageHeaderAccessor.wrap(message);
			initHeaders(simpAccessor);
		}

		simpAccessor.setDestination(destination);
		simpAccessor.setMessageTypeIfNotSet(SimpMessageType.MESSAGE);
		message = MessageBuilder.createMessage(message.getPayload(), simpAccessor.getMessageHeaders());
		sendInternal(message);
	}
