	@Test
	public void attemptToReadDisallowedPriorityPropertyIsNotFatal() throws JMSException {
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public int getJMSPriority() throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		assertAttemptReadDisallowedPropertyIsNotFatal(jmsMessage, JmsHeaders.PRIORITY);
	}
