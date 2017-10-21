	@Override
	@Nullable
	public Message sendAndReceive(MessageCreator messageCreator) throws JmsException {
		Destination defaultDestination = getDefaultDestination();
		if (defaultDestination != null) {
			return sendAndReceive(defaultDestination, messageCreator);
		}
		else {
			return sendAndReceive(getRequiredDefaultDestinationName(), messageCreator);
		}
	}
