	@Nullable
	protected Destination resolveDefaultResponseDestination(Session session) throws JMSException {
		if (this.defaultResponseDestination instanceof Destination) {
			return (Destination) this.defaultResponseDestination;
		}
		if (this.defaultResponseDestination instanceof DestinationNameHolder) {
			DestinationNameHolder nameHolder = (DestinationNameHolder) this.defaultResponseDestination;
			return getDestinationResolver().resolveDestinationName(session, nameHolder.name, nameHolder.isTopic);
		}
		return null;
	}
