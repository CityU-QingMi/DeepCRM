	@Test
	public void sendWithConversionException() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		StompHeaders stompHeaders = new StompHeaders();
		stompHeaders.setDestination("/topic/foo");
		stompHeaders.setContentType(MimeTypeUtils.APPLICATION_JSON);
		String payload = "{'foo':'bar'}";

		this.expected.expect(MessageConversionException.class);
		this.session.send(stompHeaders, payload);
		verifyNoMoreInteractions(this.connection);
	}
