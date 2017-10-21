	@Test
	public void handleTransportError() throws Exception {

		Exception exception = new Exception("transport error");

		willThrow(new IllegalStateException("error"))
			.given(this.delegate).handleTransportError(this.session, exception);

		this.decorator.handleTransportError(this.session, exception);

		assertEquals(CloseStatus.SERVER_ERROR, this.session.getCloseStatus());
	}
