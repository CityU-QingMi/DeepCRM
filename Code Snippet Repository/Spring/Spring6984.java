	@Test
	public void sendPropertyInjection() {
		JmsMessagingTemplate t = new JmsMessagingTemplate();
		t.setJmsTemplate(this.jmsTemplate);
		t.setDefaultDestinationName("myQueue");
		t.afterPropertiesSet();
		Message<String> message = createTextMessage();

		t.send(message);
		verify(this.jmsTemplate).send(eq("myQueue"), this.messageCreator.capture());
		assertTextMessage(this.messageCreator.getValue());
	}
