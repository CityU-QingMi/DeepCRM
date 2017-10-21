	@Test
	public void fallbackAfterTransportError() throws Exception {
		DefaultTransportRequest request1 = createTransportRequest(this.webSocketTransport, TransportType.WEBSOCKET);
		DefaultTransportRequest request2 = createTransportRequest(this.xhrTransport, TransportType.XHR_STREAMING);
		request1.setFallbackRequest(request2);
		request1.connect(null, this.connectFuture);

		// Transport error => fallback
		this.webSocketTransport.getConnectCallback().onFailure(new IOException("Fake exception 1"));
		assertFalse(this.connectFuture.isDone());
		assertTrue(this.xhrTransport.invoked());

		// Transport error => no more fallback
		this.xhrTransport.getConnectCallback().onFailure(new IOException("Fake exception 2"));
		assertTrue(this.connectFuture.isDone());
		this.thrown.expect(ExecutionException.class);
		this.thrown.expectMessage("Fake exception 2");
		this.connectFuture.get();
	}
