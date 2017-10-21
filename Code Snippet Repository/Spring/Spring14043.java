	@Test
	public void doHandshakeWithTaskExecutor() throws Exception {

		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.setSecWebSocketProtocol(Arrays.asList("echo"));

		this.client.setTaskExecutor(new SimpleAsyncTaskExecutor());
		this.wsSession = this.client.doHandshake(new TextWebSocketHandler(), headers, new URI(this.wsUrl)).get();

		assertEquals(this.wsUrl, this.wsSession.getUri().toString());
		assertEquals("echo", this.wsSession.getAcceptedProtocol());
	}
