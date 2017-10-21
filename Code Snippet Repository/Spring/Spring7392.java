	@Test
	public void sendAndReceive() {
		Message<?> requestMessage = new GenericMessage<Object>("request");
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		Message<?> actual = this.template.sendAndReceive("myChannel", requestMessage);

		assertEquals(requestMessage, this.template.message);
		assertSame(responseMessage, actual);
		assertSame(this.myChannel, this.template.messageChannel);
	}
