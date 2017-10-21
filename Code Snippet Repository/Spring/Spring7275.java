	private void invokeHandler(StompFrameHandler handler, Message<byte[]> message, StompHeaders stompHeaders) {
		if (message.getPayload().length == 0) {
			handler.handleFrame(stompHeaders, null);
			return;
		}
		Type payloadType = handler.getPayloadType(stompHeaders);
		Class<?> resolvedType = ResolvableType.forType(payloadType).resolve();
		if (resolvedType == null) {
			throw new MessageConversionException("Unresolvable payload type [" + payloadType +
					"] from handler type [" + handler.getClass() + "]");
		}
		Object object = getMessageConverter().fromMessage(message, resolvedType);
		if (object == null) {
			throw new MessageConversionException("No suitable converter for payload type [" + payloadType +
					"] from handler type [" + handler.getClass() + "]");
		}
		handler.handleFrame(stompHeaders, object);
	}
