	@Test
	public void closeStatusNormal() throws Exception {

		BlockingSession delegate = new BlockingSession();
		delegate.setOpen(true);
		WebSocketSession decorator = new ConcurrentWebSocketSessionDecorator(delegate, 10 * 1000, 1024);

		decorator.close(CloseStatus.PROTOCOL_ERROR);
		assertEquals(CloseStatus.PROTOCOL_ERROR, delegate.getCloseStatus());

		decorator.close(CloseStatus.SERVER_ERROR);
		assertEquals("Should have been ignored", CloseStatus.PROTOCOL_ERROR, delegate.getCloseStatus());
	}
