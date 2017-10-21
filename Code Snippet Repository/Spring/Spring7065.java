	@Test
	public void attemptToReadDisallowedUserPropertyIsNotFatal() throws JMSException {
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public Object getObjectProperty(String name) throws JMSException {
				if (name.equals("fail")) {
					throw new JMSException("illegal property");
				}
				else {
					return super.getObjectProperty(name);
				}
			}
		};
		jmsMessage.setBooleanProperty("fail", true);
		assertAttemptReadDisallowedPropertyIsNotFatal(jmsMessage, "fail");
	}
