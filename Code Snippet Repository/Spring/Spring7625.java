	@Test
	public void processConnectHeadersWithExplicitHeartbeat() throws Exception {

		StompHeaders connectHeaders = new StompHeaders();
		connectHeaders.setHeartbeat(new long[] {15000, 15000});
		connectHeaders = this.stompClient.processConnectHeaders(connectHeaders);

		assertNotNull(connectHeaders);
		assertArrayEquals(new long[] {15000, 15000}, connectHeaders.getHeartbeat());
	}
