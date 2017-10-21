	@Test
	public void handleInitialRequest() throws Exception {

		this.session.handleInitialRequest(this.request, this.response, this.frameFormat);

		assertEquals("hhh\no", this.servletResponse.getContentAsString());
		assertTrue(this.servletRequest.isAsyncStarted());

		verify(this.webSocketHandler).afterConnectionEstablished(this.session);
	}
