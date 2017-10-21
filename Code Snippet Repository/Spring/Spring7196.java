	private void sendInternal(Message<?> message) {
		String destination = SimpMessageHeaderAccessor.getDestination(message.getHeaders());
		Assert.notNull(destination, "Destination header required");

		long timeout = this.sendTimeout;
		boolean sent = (timeout >= 0 ? this.messageChannel.send(message, timeout) : this.messageChannel.send(message));

		if (!sent) {
			throw new MessageDeliveryException(message,
					"Failed to send message to destination '" + destination + "' within timeout: " + timeout);
		}
	}
