	@Test
	public void convertAndSendToDestination() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		String response = this.template.convertSendAndReceive("somewhere", "request", String.class);

		assertEquals("somewhere", this.template.destination);
		assertSame("request", this.template.requestMessage.getPayload());
		assertSame("response", response);
	}
