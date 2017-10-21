	@Test
	public void handleClientMessageProcessingError() throws Exception {

		Exception ex = new Exception("fake exception");
		Message<byte[]> actual = this.handler.handleClientMessageProcessingError(null, ex);

		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(actual, StompHeaderAccessor.class);
		assertNotNull(accessor);
		assertEquals(StompCommand.ERROR, accessor.getCommand());
		assertEquals(ex.getMessage(), accessor.getMessage());
		assertArrayEquals(new byte[0], actual.getPayload());
	}
