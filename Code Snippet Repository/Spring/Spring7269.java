	@SuppressWarnings("")
	private Message<byte[]> createMessage(StompHeaderAccessor accessor, @Nullable Object payload) {
		accessor.updateSimpMessageHeadersFromStompHeaders();
		Message<byte[]> message;
		if (payload == null) {
			message = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());
		}
		else if (payload instanceof byte[]) {
			message = MessageBuilder.createMessage((byte[]) payload, accessor.getMessageHeaders());
		}
		else {
			message = (Message<byte[]>) getMessageConverter().toMessage(payload, accessor.getMessageHeaders());
			accessor.updateStompHeadersFromSimpMessageHeaders();
			if (message == null) {
				throw new MessageConversionException("Unable to convert payload with type='" +
						payload.getClass().getName() + "', contentType='" + accessor.getContentType() +
						"', converter=[" + getMessageConverter() + "]");
			}
		}
		return message;
	}
