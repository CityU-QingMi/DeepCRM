	@Test
	public void attemptToReadDisallowedMessageIdPropertyIsNotFatal() throws JMSException {
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public String getJMSMessageID() throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		assertAttemptReadDisallowedPropertyIsNotFatal(jmsMessage, JmsHeaders.MESSAGE_ID);
	}
