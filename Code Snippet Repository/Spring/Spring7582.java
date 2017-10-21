	@Test
	public void afterConnected() throws Exception {
		assertFalse(this.session.isConnected());
		this.connectHeaders.setHost("my-host");
		this.connectHeaders.setHeartbeat(new long[] {11, 12});

		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.CONNECT, accessor.getCommand());
		assertEquals("my-host", accessor.getHost());
		assertThat(accessor.getAcceptVersion(), containsInAnyOrder("1.1", "1.2"));
		assertArrayEquals(new long[] {11, 12}, accessor.getHeartbeat());
	}
