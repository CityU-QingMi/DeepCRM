	@Test
	public void jmsReadOnlyPropertiesNotMapped() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader(JmsHeaders.DESTINATION, new Destination() {})
				.setHeader(JmsHeaders.DELIVERY_MODE, DeliveryMode.NON_PERSISTENT)
				.setHeader(JmsHeaders.EXPIRATION, 1000L)
				.setHeader(JmsHeaders.MESSAGE_ID, "abc-123")
				.setHeader(JmsHeaders.PRIORITY, 9)
				.setHeader(JmsHeaders.REDELIVERED, true)
				.setHeader(JmsHeaders.TIMESTAMP, System.currentTimeMillis())
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage();
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		assertNull(jmsMessage.getJMSDestination());
		assertEquals(DeliveryMode.PERSISTENT, jmsMessage.getJMSDeliveryMode());
		assertEquals(0, jmsMessage.getJMSExpiration());
		assertNull(jmsMessage.getJMSMessageID());
		assertEquals(javax.jms.Message.DEFAULT_PRIORITY, jmsMessage.getJMSPriority());
		assertFalse(jmsMessage.getJMSRedelivered());
		assertEquals(0, jmsMessage.getJMSTimestamp());
	}
