	@Test
	public void heartbeatTasks() throws Exception {
		this.session.afterConnected(this.connection);
		verify(this.connection).send(any());

		this.connectHeaders.setHeartbeat(new long[] {10000, 10000});

		StompHeaderAccessor connected = StompHeaderAccessor.create(StompCommand.CONNECTED);
		connected.setVersion("1.2");
		connected.setHeartbeat(10000, 10000);
		connected.setLeaveMutable(true);
		this.session.handleMessage(MessageBuilder.createMessage(new byte[0], connected.getMessageHeaders()));

		ArgumentCaptor<Runnable> writeTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
		ArgumentCaptor<Runnable> readTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.connection).onWriteInactivity(writeTaskCaptor.capture(), any(Long.class));
		verify(this.connection).onReadInactivity(readTaskCaptor.capture(), any(Long.class));

		Runnable writeTask = writeTaskCaptor.getValue();
		Runnable readTask = readTaskCaptor.getValue();
		assertNotNull(writeTask);
		assertNotNull(readTask);

		writeTask.run();
		StompHeaderAccessor accessor = StompHeaderAccessor.createForHeartbeat();
		Message<byte[]> message = MessageBuilder.createMessage(new byte[] {'\n'}, accessor.getMessageHeaders());
		verify(this.connection).send(eq(message));
		verifyNoMoreInteractions(this.connection);

		reset(this.sessionHandler);
		readTask.run();
		verify(this.sessionHandler).handleTransportError(same(this.session), any(IllegalStateException.class));
		verifyNoMoreInteractions(this.sessionHandler);
	}
