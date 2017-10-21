	@Test
	public void receiveAndConvertWithConversion() {
		javax.jms.Message jmsMessage = createJmsTextMessage("123");
		given(this.jmsTemplate.receive("myQueue")).willReturn(jmsMessage);

		this.messagingTemplate.setMessageConverter(new GenericMessageConverter());

		Integer payload = this.messagingTemplate.receiveAndConvert("myQueue", Integer.class);
		assertEquals(Integer.valueOf(123), payload);
		verify(this.jmsTemplate).receive("myQueue");
	}
