	@Test
	public void eventPublication() {

		TestPublisher publisher = new TestPublisher();

		this.protocolHandler.setApplicationEventPublisher(publisher);
		this.protocolHandler.afterSessionStarted(this.session, this.channel);

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.CONNECT);
		Message<byte[]> message = MessageBuilder.createMessage(EMPTY_PAYLOAD, headers.getMessageHeaders());
		TextMessage textMessage = new TextMessage(new StompEncoder().encode(message));
		this.protocolHandler.handleMessageFromClient(this.session, textMessage, this.channel);

		headers = StompHeaderAccessor.create(StompCommand.CONNECTED);
		message = MessageBuilder.createMessage(EMPTY_PAYLOAD, headers.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, message);

		headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
		message = MessageBuilder.createMessage(EMPTY_PAYLOAD, headers.getMessageHeaders());
		textMessage = new TextMessage(new StompEncoder().encode(message));
		this.protocolHandler.handleMessageFromClient(this.session, textMessage, this.channel);

		headers = StompHeaderAccessor.create(StompCommand.UNSUBSCRIBE);
		message = MessageBuilder.createMessage(EMPTY_PAYLOAD, headers.getMessageHeaders());
		textMessage = new TextMessage(new StompEncoder().encode(message));
		this.protocolHandler.handleMessageFromClient(this.session, textMessage, this.channel);

		this.protocolHandler.afterSessionEnded(this.session, CloseStatus.BAD_DATA, this.channel);

		assertEquals("Unexpected events " + publisher.events, 5, publisher.events.size());
		assertEquals(SessionConnectEvent.class, publisher.events.get(0).getClass());
		assertEquals(SessionConnectedEvent.class, publisher.events.get(1).getClass());
		assertEquals(SessionSubscribeEvent.class, publisher.events.get(2).getClass());
		assertEquals(SessionUnsubscribeEvent.class, publisher.events.get(3).getClass());
		assertEquals(SessionDisconnectEvent.class, publisher.events.get(4).getClass());
	}
