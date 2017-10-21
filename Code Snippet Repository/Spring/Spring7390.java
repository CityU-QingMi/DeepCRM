	@Test
	public void convertAndSendPayloadWithPostProcessor() {
		this.template.convertAndSend("myChannel", "payload", this.postProcessor);

		assertSame(this.myChannel, this.template.messageChannel);
		assertNotNull(this.template.message);
		assertEquals("payload", this.template.message.getPayload());

		assertNotNull(this.postProcessor.getMessage());
		assertSame(this.postProcessor.getMessage(), this.template.message);
	}
