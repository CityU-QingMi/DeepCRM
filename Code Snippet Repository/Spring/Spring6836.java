	protected Message createResponseMessage(Message request, Session session, RemoteInvocationResult result)
			throws JMSException {

		Message response = this.messageConverter.toMessage(result, session);
		String correlation = request.getJMSCorrelationID();
		if (correlation == null) {
			correlation = request.getJMSMessageID();
		}
		response.setJMSCorrelationID(correlation);
		return response;
	}
