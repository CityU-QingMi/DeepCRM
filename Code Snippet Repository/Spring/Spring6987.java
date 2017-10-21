	@Test
	public void convertAndSendPayloadAndHeaders() throws JMSException {
		Destination destination = new Destination() {};
		Map<String, Object> headers = new HashMap<>();
		headers.put("foo", "bar");

		this.messagingTemplate.convertAndSend(destination, "Hello", headers);
		verify(this.jmsTemplate).send(eq(destination), this.messageCreator.capture());
		assertTextMessage(this.messageCreator.getValue()); // see createTextMessage
	}
