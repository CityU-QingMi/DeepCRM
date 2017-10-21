	@Test
	public void handleSuccessiveRequest() throws Exception {

		this.session.getMessageCache().add("x");
		this.session.handleSuccessiveRequest(this.request, this.response, this.frameFormat);

		assertTrue(this.servletRequest.isAsyncStarted());
		assertTrue(this.session.wasHeartbeatScheduled());
		assertTrue(this.session.wasCacheFlushed());
		assertEquals("hhh\n", this.servletResponse.getContentAsString());

		verifyNoMoreInteractions(this.webSocketHandler);
	}
