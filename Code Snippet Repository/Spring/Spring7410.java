	@Test
	public void sendAndReceiveToDestination() {
		Message<?> requestMessage = new GenericMessage<Object>("request");
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		Message<?> actual = this.template.sendAndReceive("somewhere", requestMessage);

		assertEquals("somewhere", this.template.destination);
		assertSame(requestMessage, this.template.requestMessage);
		assertSame(responseMessage, actual);
	}
