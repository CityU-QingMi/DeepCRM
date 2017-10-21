	@Test
	public void convertAndSendPayloadWithPostProcessorToDestination() {
		this.template.convertAndSend("somewhere", "payload", this.postProcessor);

		assertEquals("somewhere", this.template.destination);
		assertNotNull(this.template.message);
		assertEquals("expected 'id' and 'timestamp' headers only", 2, this.template.message.getHeaders().size());
		assertEquals("payload", this.template.message.getPayload());

		assertNotNull(this.postProcessor.getMessage());
		assertSame(this.template.message, this.postProcessor.getMessage());
	}
