	@Override
	@Nullable
	public <T> T receiveAndConvert(Class<T> targetClass) {
		Destination defaultDestination = getDefaultDestination();
		if (defaultDestination != null) {
			return receiveAndConvert(defaultDestination, targetClass);
		}
		else {
			return receiveAndConvert(getRequiredDefaultDestinationName(), targetClass);
		}
	}
