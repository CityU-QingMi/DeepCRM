	@Test
	public void attemptToReadDisallowedDeliveryModePropertyIsNotFatal() throws JMSException {
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public int getJMSDeliveryMode() throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		assertAttemptReadDisallowedPropertyIsNotFatal(jmsMessage, JmsHeaders.DELIVERY_MODE);
	}
