	@Test
	public void convertAndSend() {
		this.template.convertAndSend("somewhere", "payload", headers, this.postProcessor);

		assertEquals("somewhere", this.template.destination);
		assertNotNull(this.template.message);
		assertEquals("value", this.template.message.getHeaders().get("key"));
		assertEquals("payload", this.template.message.getPayload());

		assertNotNull(this.postProcessor.getMessage());
		assertSame(this.template.message, this.postProcessor.getMessage());
	}
