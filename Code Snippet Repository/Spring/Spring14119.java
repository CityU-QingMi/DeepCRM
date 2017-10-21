	@Test
	public void handleMessageFromClientWithInvalidStompCommand() {

		TextMessage textMessage = new TextMessage("FOO\n\n\0");

		this.protocolHandler.afterSessionStarted(this.session, this.channel);
		this.protocolHandler.handleMessageFromClient(this.session, textMessage, this.channel);

		verifyZeroInteractions(this.channel);
		assertEquals(1, this.session.getSentMessages().size());
		TextMessage actual = (TextMessage) this.session.getSentMessages().get(0);
		assertTrue(actual.getPayload().startsWith("ERROR"));
	}
