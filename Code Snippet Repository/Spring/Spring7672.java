	@Test
	public void handleMessage() {
		TestSimpUser simpUser = new TestSimpUser("joe");
		simpUser.addSessions(new TestSimpSession("123"));
		when(this.registry.getUser("joe")).thenReturn(simpUser);
		given(this.brokerChannel.send(Mockito.any(Message.class))).willReturn(true);
		this.handler.handleMessage(createWith(SimpMessageType.MESSAGE, "joe", "123", "/user/joe/queue/foo"));

		ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
		Mockito.verify(this.brokerChannel).send(captor.capture());

		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(captor.getValue());
		assertEquals("/queue/foo-user123", accessor.getDestination());
		assertEquals("/user/queue/foo", accessor.getFirstNativeHeader(ORIGINAL_DESTINATION));
	}
