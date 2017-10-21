	@Override
	@Nullable
	public Message<?> sendAndReceive(Message<?> requestMessage) {
		Destination defaultDestination = getDefaultDestination();
		if (defaultDestination != null) {
			return sendAndReceive(defaultDestination, requestMessage);
		}
		else {
			return sendAndReceive(getRequiredDefaultDestinationName(), requestMessage);
		}
	}
