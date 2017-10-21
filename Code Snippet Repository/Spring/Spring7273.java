	@Override
	public void disconnect() {
		this.closing = true;
		try {
			StompHeaderAccessor accessor = createHeaderAccessor(StompCommand.DISCONNECT);
			Message<byte[]> message = createMessage(accessor, EMPTY_PAYLOAD);
			execute(message);
		}
		finally {
			resetConnection();
		}
	}
