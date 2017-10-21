	@Test
	public void convertSendAndReceiveWithPostProcessor() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		String actual = this.template.convertSendAndReceive("myChannel", "request", String.class, this.postProcessor);

		assertEquals("request", this.template.message.getPayload());
		assertSame("request", this.postProcessor.getMessage().getPayload());
		assertSame("response", actual);
		assertSame(this.myChannel, this.template.messageChannel);
	}
