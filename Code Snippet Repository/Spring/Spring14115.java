	@Test
	public void handleMessageFromClient() {

		TextMessage textMessage = StompTextMessageBuilder.create(StompCommand.CONNECT).headers(
				"login:guest", "passcode:guest", "accept-version:1.1,1.0", "heart-beat:10000,10000").build();

		this.protocolHandler.afterSessionStarted(this.session, this.channel);
		this.protocolHandler.handleMessageFromClient(this.session, textMessage, this.channel);

		verify(this.channel).send(this.messageCaptor.capture());
		Message<?> actual = this.messageCaptor.getValue();
		assertNotNull(actual);

		assertEquals("s1", SimpMessageHeaderAccessor.getSessionId(actual.getHeaders()));
		assertNotNull(SimpMessageHeaderAccessor.getSessionAttributes(actual.getHeaders()));
		assertNotNull(SimpMessageHeaderAccessor.getUser(actual.getHeaders()));
		assertEquals("joe", SimpMessageHeaderAccessor.getUser(actual.getHeaders()).getName());
		assertNotNull(SimpMessageHeaderAccessor.getHeartbeat(actual.getHeaders()));
		assertArrayEquals(new long[] {10000, 10000}, SimpMessageHeaderAccessor.getHeartbeat(actual.getHeaders()));

		StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(actual);
		assertEquals(StompCommand.CONNECT, stompAccessor.getCommand());
		assertEquals("guest", stompAccessor.getLogin());
		assertEquals("guest", stompAccessor.getPasscode());
		assertArrayEquals(new long[] {10000, 10000}, stompAccessor.getHeartbeat());
		assertEquals(new HashSet<>(Arrays.asList("1.1","1.0")), stompAccessor.getAcceptVersion());
		assertEquals(0, this.session.getSentMessages().size());
	}
