	@Test
	public void attemptToReadDisallowedCorrelationIdPropertyIsNotFatal() throws JMSException {
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public String getJMSCorrelationID() throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		assertAttemptReadDisallowedPropertyIsNotFatal(jmsMessage, JmsHeaders.CORRELATION_ID);
	}
