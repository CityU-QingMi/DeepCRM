	@Test
	public void attemptToReadDisallowedTypePropertyIsNotFatal() throws JMSException {
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public String getJMSType() throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		assertAttemptReadDisallowedPropertyIsNotFatal(jmsMessage, JmsHeaders.TYPE);
	}
