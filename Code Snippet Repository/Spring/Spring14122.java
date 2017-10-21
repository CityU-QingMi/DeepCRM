	@Test
	public void webSocketScope() {

		Runnable runnable = Mockito.mock(Runnable.class);
		SimpAttributes simpAttributes = new SimpAttributes(this.session.getId(), this.session.getAttributes());
		simpAttributes.setAttribute("name", "value");
		simpAttributes.registerDestructionCallback("name", runnable);

		MessageChannel testChannel = new MessageChannel() {
			@Override
			public boolean send(Message<?> message) {
				SimpAttributes simpAttributes = SimpAttributesContextHolder.currentAttributes();
				assertThat(simpAttributes.getAttribute("name"), is("value"));
				return true;
			}
			@Override
			public boolean send(Message<?> message, long timeout) {
				return false;
			}
		};

		this.protocolHandler.afterSessionStarted(this.session, this.channel);

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.CONNECT);
		Message<byte[]> message = MessageBuilder.createMessage(EMPTY_PAYLOAD, headers.getMessageHeaders());
		TextMessage textMessage = new TextMessage(new StompEncoder().encode(message));

		this.protocolHandler.handleMessageFromClient(this.session, textMessage, testChannel);
		assertEquals(Collections.<WebSocketMessage<?>>emptyList(), session.getSentMessages());

		this.protocolHandler.afterSessionEnded(this.session, CloseStatus.BAD_DATA, testChannel);
		assertEquals(Collections.<WebSocketMessage<?>>emptyList(), this.session.getSentMessages());
		verify(runnable, times(1)).run();
	}
