	private javax.jms.Message createJmsTextMessage(String payload) {
		try {
			StubTextMessage jmsMessage = new StubTextMessage(payload);
			jmsMessage.setStringProperty("foo", "bar");
			return jmsMessage;
		}
		catch (JMSException e) {
			throw new IllegalStateException("Should not happen", e);
		}
	}
