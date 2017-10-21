	@Test
	public void convertSendAndReceiveWithHeadersAndPostProcessor() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		String actual = this.template.convertSendAndReceive("myChannel", "request", this.headers,
				String.class, this.postProcessor);

		assertEquals("value", this.template.message.getHeaders().get("key"));
		assertEquals("request", this.template.message.getPayload());
		assertSame("request", this.postProcessor.getMessage().getPayload());
		assertSame("response", actual);
		assertSame(this.myChannel, this.template.messageChannel);
	}
