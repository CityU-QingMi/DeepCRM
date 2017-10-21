	@Override
	@Nullable
	public Message<byte[]> handleErrorMessageToClient(Message<byte[]> errorMessage) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(errorMessage, StompHeaderAccessor.class);
		Assert.notNull(accessor, "No StompHeaderAccessor");
		if (!accessor.isMutable()) {
			accessor = StompHeaderAccessor.wrap(errorMessage);
		}
		return handleInternal(accessor, errorMessage.getPayload(), null, null);
	}
