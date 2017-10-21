	@Override
	protected Object preProcessResponse(Object result) {
		MethodParameter returnType = getHandlerMethod().getReturnType();
		if (result instanceof Message) {
			return MessageBuilder.fromMessage((Message<?>) result)
					.setHeader(AbstractMessageSendingTemplate.CONVERSION_HINT_HEADER, returnType).build();
		}
		return MessageBuilder.withPayload(result).setHeader(
				AbstractMessageSendingTemplate.CONVERSION_HINT_HEADER, returnType).build();
	}
