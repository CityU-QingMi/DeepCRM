	@Nullable
	protected Message<?> convertJmsMessage(@Nullable javax.jms.Message message) {
		if (message == null) {
			return null;
		}
		try {
			return (Message<?>) getJmsMessageConverter().fromMessage(message);
		}
		catch (Exception ex) {
			throw new MessageConversionException("Could not convert '" + message + "'", ex);
		}
	}
