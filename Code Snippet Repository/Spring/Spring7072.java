	@Test
	public void attemptToWriteDisallowedCorrelationIdNumberPropertyIsNotFatal() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader(JmsHeaders.CORRELATION_ID, 123)
				.setHeader("foo", "bar")
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public void setJMSCorrelationID(String correlationId) throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		assertNull(jmsMessage.getJMSCorrelationID());
		assertNotNull(jmsMessage.getStringProperty("foo"));
		assertEquals("bar", jmsMessage.getStringProperty("foo"));
	}
