	@Test
	public void userDefinedPropertyWithUnsupportedType() throws JMSException {
		Destination destination = new Destination() {};
		Message<String> message = initBuilder()
				.setHeader("destination", destination)
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage();
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		Object value = jmsMessage.getObjectProperty("destination");
		assertNull(value);
	}
