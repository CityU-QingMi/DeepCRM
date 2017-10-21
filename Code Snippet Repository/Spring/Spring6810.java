	protected Destination getResponseDestination(Message request, Message response, Session session)
			throws JMSException {

		Destination replyTo = request.getJMSReplyTo();
		if (replyTo == null) {
			replyTo = resolveDefaultResponseDestination(session);
			if (replyTo == null) {
				throw new InvalidDestinationException("Cannot determine response destination: " +
						"Request message does not contain reply-to destination, and no default response destination set.");
			}
		}
		return replyTo;
	}
