	@Nullable
	private String getDisconnectReceipt(SimpMessageHeaderAccessor simpHeaders) {
		String name = StompHeaderAccessor.DISCONNECT_MESSAGE_HEADER;
		Message<?> message = (Message<?>) simpHeaders.getHeader(name);
		if (message != null) {
			StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
			if (accessor != null) {
				return accessor.getReceipt();
			}
		}
		return null;
	}
