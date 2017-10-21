	@Nullable
	public Destination resolveDestination(DestinationResolver destinationResolver, Session session)
			throws JMSException {

		if (this.destination instanceof Destination) {
			return (Destination) this.destination;
		}
		if (this.destination instanceof DestinationNameHolder) {
			DestinationNameHolder nameHolder = (DestinationNameHolder) this.destination;
			return destinationResolver.resolveDestinationName(session,
					nameHolder.destinationName, nameHolder.pubSubDomain);
		}
		return null;
	}
