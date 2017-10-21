	@Test
	public void connectReceiveAndCloseWithStompFrame() throws Exception {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.SEND);
		accessor.setDestination("/destination");
		MessageHeaders headers = accessor.getMessageHeaders();
		Message<byte[]> message = MessageBuilder.createMessage("body".getBytes(StandardCharsets.UTF_8), headers);
		byte[] bytes = new StompEncoder().encode(message);
		TextMessage textMessage = new TextMessage(bytes);
		SockJsFrame frame = SockJsFrame.messageFrame(new Jackson2SockJsMessageCodec(), textMessage.getPayload());

		String body = "o\n" + frame.getContent() + "\n" + "c[3000,\"Go away!\"]";
		ClientHttpResponse response = response(HttpStatus.OK, body);
		connect(response);

		verify(this.webSocketHandler).afterConnectionEstablished(any());
		verify(this.webSocketHandler).handleMessage(any(), eq(textMessage));
		verify(this.webSocketHandler).afterConnectionClosed(any(), eq(new CloseStatus(3000, "Go away!")));
		verifyNoMoreInteractions(this.webSocketHandler);
	}
