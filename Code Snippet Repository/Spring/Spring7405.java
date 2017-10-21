	@Test
	public void receive() {
		Message<?> expected = new GenericMessage<>("payload");
		this.template.setDefaultDestination("home");
		this.template.setReceiveMessage(expected);
		Message<?> actual = this.template.receive();

		assertEquals("home", this.template.destination);
		assertSame(expected, actual);
	}
