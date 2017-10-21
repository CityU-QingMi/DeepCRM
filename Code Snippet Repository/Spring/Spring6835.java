	protected void writeRemoteInvocationResult(
			Message requestMessage, Session session, RemoteInvocationResult result) throws JMSException {

		Message response = createResponseMessage(requestMessage, session, result);
		MessageProducer producer = session.createProducer(requestMessage.getJMSReplyTo());
		try {
			producer.send(response);
		}
		finally {
			JmsUtils.closeMessageProducer(producer);
		}
	}
