	@Test
	public void handleMessage() throws Exception {

		TextMessage message = new TextMessage("payload");

		willThrow(new IllegalStateException("error"))
			.given(this.delegate).handleMessage(this.session, message);

		this.decorator.handleMessage(this.session, message);

		assertEquals(CloseStatus.SERVER_ERROR, this.session.getCloseStatus());
	}
