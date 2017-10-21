	@Override
	@Nullable
	public <T> T receiveAndConvert(String destinationName, Class<T> targetClass) throws MessagingException {
		Message<?> message = doReceive(destinationName);
		if (message != null) {
			return doConvert(message, targetClass);
		}
		else {
			return null;
		}
	}
