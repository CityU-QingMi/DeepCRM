	@Test
	public void convertAndSendToDestinationWithPostProcessor() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		String response = this.template.convertSendAndReceive("somewhere", "request", String.class, this.postProcessor);

		assertEquals("somewhere", this.template.destination);
		assertSame("request", this.template.requestMessage.getPayload());
		assertSame("response", response);
		assertSame(this.postProcessor.getMessage(), this.template.requestMessage);
	}
