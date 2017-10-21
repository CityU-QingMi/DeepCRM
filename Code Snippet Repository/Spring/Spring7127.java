	@Override
	public Message<?> toMessage(Object payload, @Nullable MessageHeaders headers) {
		if (headers != null) {
			MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(headers, MessageHeaderAccessor.class);
			if (accessor != null && accessor.isMutable()) {
				return MessageBuilder.createMessage(payload, accessor.getMessageHeaders());
			}
		}
		return MessageBuilder.withPayload(payload).copyHeaders(headers).build();
	}
