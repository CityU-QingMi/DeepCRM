	@Test
	@SuppressWarnings("")
	public void listenableFutureSuccess() {
		Message emptyMessage = (Message) MessageBuilder.withPayload(new byte[0]).build();
		given(this.channel.send(any(Message.class))).willReturn(true);
		given(this.converter.toMessage(any(), any(MessageHeaders.class))).willReturn(emptyMessage);

		ListenableFutureController controller = new ListenableFutureController();
		this.messageHandler.registerHandler(controller);
		this.messageHandler.setDestinationPrefixes(Arrays.asList("/app1", "/app2/"));

		Message<?> message = createMessage("/app1/listenable-future/success");
		this.messageHandler.handleMessage(message);

		assertNotNull(controller.future);
		controller.future.run();
		verify(this.converter).toMessage(this.payloadCaptor.capture(), any(MessageHeaders.class));
		assertEquals("foo", this.payloadCaptor.getValue());
	}
