	@Override
	@Nullable
	public <T> T receiveAndConvert(D destination, Class<T> targetClass) {
		Message<?> message = doReceive(destination);
		if (message != null) {
			return doConvert(message, targetClass);
		}
		else {
			return null;
		}
	}
