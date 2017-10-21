	@Test
	public void heartbeatDefaultValueSetWithoutScheduler() throws Exception {
		WebSocketStompClient stompClient = new WebSocketStompClient(mock(WebSocketClient.class));
		stompClient.setDefaultHeartbeat(new long[] {5, 5});
		try {
			stompClient.processConnectHeaders(null);
			fail("Expected IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// ignore
		}
	}
