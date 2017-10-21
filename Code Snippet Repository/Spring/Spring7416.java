	@Test
	public void convertAndSendToDestinationWithHeadersAndPostProcessor() {
		Message<?> responseMessage = new GenericMessage<Object>("response");
		this.template.setReceiveMessage(responseMessage);
		String response = this.template.convertSendAndReceive("somewhere", "request", this.headers,
				String.class, this.postProcessor);

		assertEquals("somewhere", this.template.destination);
		assertEquals("value", this.template.requestMessage.getHeaders().get("key"));
		assertSame("request", this.template.requestMessage.getPayload());
		assertSame("response", response);
		assertSame(this.postProcessor.getMessage(), this.template.requestMessage);
	}
