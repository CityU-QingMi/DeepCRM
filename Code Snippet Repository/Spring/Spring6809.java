	private Destination getResponseDestination(Message request, Message response, Session session, Object result)
			throws JMSException {

		if (result instanceof JmsResponse) {
			JmsResponse<?> jmsResponse = (JmsResponse) result;
			Destination destination = jmsResponse.resolveDestination(getDestinationResolver(), session);
			if (destination != null) {
				return destination;
			}
		}
		return getResponseDestination(request, response, session);
	}
