	@Test
	public void heartbeatValues() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		this.connectHeaders.setHeartbeat(new long[] {10000, 10000});

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECTED);
		accessor.setVersion("1.2");
		accessor.setHeartbeat(20000, 20000);
		accessor.setLeaveMutable(true);
		this.session.handleMessage(MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders()));

		ArgumentCaptor<Long> writeInterval = ArgumentCaptor.forClass(Long.class);
		verify(this.connection).onWriteInactivity(any(Runnable.class), writeInterval.capture());
		assertEquals(20000, (long) writeInterval.getValue());

		ArgumentCaptor<Long> readInterval = ArgumentCaptor.forClass(Long.class);
		verify(this.connection).onReadInactivity(any(Runnable.class), readInterval.capture());
		assertEquals(60000, (long) readInterval.getValue());
	}
