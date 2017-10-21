	@Test
	public void convertAndSendToDestinationWithHeaders() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		String response = this.template.convertSendAndReceive("somewhere", "request", this.headers, String.class);

		assertEquals("somewhere", this.template.destination);
		assertEquals("value", this.template.requestMessage.getHeaders().get("key"));
		assertSame("request", this.template.requestMessage.getPayload());
		assertSame("response", response);
	}
