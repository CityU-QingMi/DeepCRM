	@Test
	public void convertAndSend() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setDefaultDestination("home");
		this.template.setReceiveMessage(responseMessage);
		String response = this.template.convertSendAndReceive("request", String.class);

		assertEquals("home", this.template.destination);
		assertSame("request", this.template.requestMessage.getPayload());
		assertSame("response", response);
	}
