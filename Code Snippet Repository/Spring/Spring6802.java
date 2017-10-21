	protected MessageConsumer createListenerConsumer(final Session session) throws JMSException {
		Destination destination = getDestination();
		if (destination == null) {
			String destinationName = getDestinationName();
			Assert.state(destinationName != null, "No destination set");
			destination = resolveDestinationName(session, destinationName);
		}
		MessageConsumer consumer = createConsumer(session, destination);

		if (this.taskExecutor != null) {
			consumer.setMessageListener(message -> taskExecutor.execute(() -> processMessage(message, session)));
		}
		else {
			consumer.setMessageListener(message -> processMessage(message, session));
		}

		return consumer;
	}
