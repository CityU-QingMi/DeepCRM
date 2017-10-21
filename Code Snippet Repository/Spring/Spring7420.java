	@Test
	public void convertAndSendPayloadWithPostProcessor() {
		this.template.setDefaultDestination("home");
		this.template.convertAndSend((Object) "payload", this.postProcessor);

		assertEquals("home", this.template.destination);
		assertNotNull(this.template.message);
		assertEquals("expected 'id' and 'timestamp' headers only", 2, this.template.message.getHeaders().size());
		assertEquals("payload", this.template.message.getPayload());

		assertNotNull(this.postProcessor.getMessage());
		assertSame(this.template.message, this.postProcessor.getMessage());
	}
