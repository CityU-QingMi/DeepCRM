	@Test
	public void convertAndSendWithPostProcessor() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setDefaultDestination("home");
		this.template.setReceiveMessage(responseMessage);
		String response = this.template.convertSendAndReceive("request", String.class, this.postProcessor);

		assertEquals("home", this.template.destination);
		assertSame("request", this.template.requestMessage.getPayload());
		assertSame("response", response);
		assertSame(this.postProcessor.getMessage(), this.template.requestMessage);
	}
