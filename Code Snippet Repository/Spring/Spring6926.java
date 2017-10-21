	private void processAndReplyWithSendTo(MessagingMessageListenerAdapter listener,
			String replyDestinationName, boolean pubSubDomain,
			QosSettings replyQosSettings) throws JMSException {
		String body = "echo text";
		String correlationId = "link-1234";
		Destination replyDestination = new Destination() {};

		DestinationResolver destinationResolver = mock(DestinationResolver.class);
		TextMessage reply = mock(TextMessage.class);
		QueueSender queueSender = mock(QueueSender.class);
		Session session = mock(Session.class);

		given(destinationResolver.resolveDestinationName(session, replyDestinationName, pubSubDomain))
				.willReturn(replyDestination);
		given(session.createTextMessage(body)).willReturn(reply);
		given(session.createProducer(replyDestination)).willReturn(queueSender);

		listener.setDestinationResolver(destinationResolver);
		StubTextMessage inputMessage = createSimpleJmsTextMessage(body);
		inputMessage.setJMSCorrelationID(correlationId);
		listener.onMessage(inputMessage, session);

		verify(destinationResolver).resolveDestinationName(session, replyDestinationName, pubSubDomain);
		verify(reply).setJMSCorrelationID(correlationId);
		if (replyQosSettings != null) {
			verify(queueSender).send(reply, replyQosSettings.getDeliveryMode(),
					replyQosSettings.getPriority(), replyQosSettings.getTimeToLive());
		}
		else {
			verify(queueSender).send(reply);
		}
		verify(queueSender).close();
	}
