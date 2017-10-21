	@Test
	public void validateJmsHeaders() throws JMSException {
		Destination destination = new Destination() {};
		Destination replyTo = new Destination() {};

		StubTextMessage jmsMessage = new StubTextMessage("test");

		jmsMessage.setJMSCorrelationID("correlation-1234");
		jmsMessage.setJMSPriority(9);
		jmsMessage.setJMSDestination(destination);
		jmsMessage.setJMSDeliveryMode(1);
		jmsMessage.setJMSExpiration(1234L);
		jmsMessage.setJMSMessageID("abcd-1234");
		jmsMessage.setJMSPriority(9);
		jmsMessage.setJMSReplyTo(replyTo);
		jmsMessage.setJMSRedelivered(true);
		jmsMessage.setJMSType("type");
		jmsMessage.setJMSTimestamp(4567L);

		Map<String, Object> mappedHeaders = new SimpleJmsHeaderMapper().toHeaders(jmsMessage);
		Message<String> message = MessageBuilder.withPayload("test").copyHeaders(mappedHeaders).build();
		JmsMessageHeaderAccessor headerAccessor = JmsMessageHeaderAccessor.wrap(message);
		assertEquals("correlation-1234", headerAccessor.getCorrelationId());
		assertEquals(destination, headerAccessor.getDestination());
		assertEquals(Integer.valueOf(1), headerAccessor.getDeliveryMode());
		assertEquals(1234L, headerAccessor.getExpiration(), 0.0);
		assertEquals("abcd-1234", headerAccessor.getMessageId());
		assertEquals(Integer.valueOf(9), headerAccessor.getPriority());
		assertEquals(replyTo, headerAccessor.getReplyTo());
		assertEquals(true, headerAccessor.getRedelivered());
		assertEquals("type", headerAccessor.getType());
		assertEquals(4567L, headerAccessor.getTimestamp(), 0.0);

		// Making sure replyChannel is not mixed with replyTo
		assertNull(headerAccessor.getReplyChannel());

	}
