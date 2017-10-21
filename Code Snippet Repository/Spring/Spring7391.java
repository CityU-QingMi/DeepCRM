	@Test
	public void convertAndSendPayloadAndHeadersWithPostProcessor() {
		this.template.convertAndSend("myChannel", "payload", this.headers, this.postProcessor);

		assertSame(this.myChannel, this.template.messageChannel);
		assertNotNull(this.template.message);
		assertEquals("value", this.template.message.getHeaders().get("key"));
		assertEquals("payload", this.template.message.getPayload());

		assertNotNull(this.postProcessor.getMessage());
		assertSame(this.postProcessor.getMessage(), this.template.message);
	}
