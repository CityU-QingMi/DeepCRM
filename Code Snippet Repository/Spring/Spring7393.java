	@Test
	public void convertSendAndReceive() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		String actual = this.template.convertSendAndReceive("myChannel", "request", String.class);

		assertEquals("request", this.template.message.getPayload());
		assertSame("response", actual);
		assertSame(this.myChannel, this.template.messageChannel);
	}
