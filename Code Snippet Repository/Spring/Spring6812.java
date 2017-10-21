	protected void sendResponse(Session session, Destination destination, Message response) throws JMSException {
		MessageProducer producer = session.createProducer(destination);
		try {
			postProcessProducer(producer, response);
			QosSettings settings = getResponseQosSettings();
			if (settings != null) {
				producer.send(response, settings.getDeliveryMode(), settings.getPriority(),
						settings.getTimeToLive());
			}
			else {
				producer.send(response);
			}
		}
		finally {
			JmsUtils.closeMessageProducer(producer);
		}
	}
