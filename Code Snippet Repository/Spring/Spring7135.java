	protected final void doSend(MessageChannel channel, Message<?> message, long timeout) {
		Assert.notNull(channel, "MessageChannel is required");

		Message<?> messageToSend = message;
		MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class);
		if (accessor != null && accessor.isMutable()) {
			accessor.removeHeader(this.sendTimeoutHeader);
			accessor.removeHeader(this.receiveTimeoutHeader);
			accessor.setImmutable();
		}
		else if (message.getHeaders().containsKey(this.sendTimeoutHeader)
				|| message.getHeaders().containsKey(this.receiveTimeoutHeader)) {
			messageToSend = MessageBuilder.fromMessage(message)
					.setHeader(this.sendTimeoutHeader, null)
					.setHeader(this.receiveTimeoutHeader, null)
					.build();
		}

		boolean sent = (timeout >= 0 ? channel.send(messageToSend, timeout) : channel.send(messageToSend));

		if (!sent) {
			throw new MessageDeliveryException(message,
					"Failed to send message to channel '" + channel + "' within timeout: " + timeout);
		}
	}
