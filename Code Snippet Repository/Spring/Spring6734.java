	@Override
	@Nullable
	public <T> T convertSendAndReceive(Object request, Class<T> targetClass, @Nullable MessagePostProcessor postProcessor) {
		Destination defaultDestination = getDefaultDestination();
		if (defaultDestination != null) {
			return convertSendAndReceive(defaultDestination, request, targetClass, postProcessor);
		}
		else {
			return convertSendAndReceive(getRequiredDefaultDestinationName(), request, targetClass, postProcessor);
		}
	}
