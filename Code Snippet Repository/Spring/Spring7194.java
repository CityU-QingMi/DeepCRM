	@Override
	public void send(Message<?> message) {
		Assert.notNull(message, "Message is required");
		String destination = SimpMessageHeaderAccessor.getDestination(message.getHeaders());
		if (destination != null) {
			sendInternal(message);
			return;
		}
		doSend(getRequiredDefaultDestination(), message);
	}
