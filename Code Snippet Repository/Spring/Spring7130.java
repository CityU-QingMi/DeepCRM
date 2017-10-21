	@SuppressWarnings("")
	@Nullable
	protected <T> T doConvert(Message<?> message, Class<T> targetClass) {
		MessageConverter messageConverter = getMessageConverter();
		T value = (T) messageConverter.fromMessage(message, targetClass);
		if (value == null) {
			throw new MessageConversionException(message, "Unable to convert payload [" + message.getPayload() +
					"] to type [" + targetClass + "] using converter [" + messageConverter + "]");
		}
		return value;
	}
