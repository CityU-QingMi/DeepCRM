	@Test
	public void convertAndSendPayload() {
		this.template.setDefaultDestination("home");
		this.template.convertAndSend("payload");

		assertEquals("home", this.template.destination);
		assertNotNull(this.template.message);
		assertEquals("expected 'id' and 'timestamp' headers only", 2, this.template.message.getHeaders().size());
		assertEquals("payload", this.template.message.getPayload());
	}
