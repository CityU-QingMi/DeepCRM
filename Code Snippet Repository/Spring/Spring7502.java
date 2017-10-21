	@Test
	@SuppressWarnings("")
	public void listenableFutureFailure() {
		Message emptyMessage = (Message) MessageBuilder.withPayload(new byte[0]).build();
		given(this.channel.send(any(Message.class))).willReturn(true);
		given(this.converter.toMessage(any(), any(MessageHeaders.class))).willReturn(emptyMessage);

		ListenableFutureController controller = new ListenableFutureController();
		this.messageHandler.registerHandler(controller);
		this.messageHandler.setDestinationPrefixes(Arrays.asList("/app1", "/app2/"));

		Message<?> message = createMessage("/app1/listenable-future/failure");
		this.messageHandler.handleMessage(message);

		controller.future.run();
		assertTrue(controller.exceptionCaught);
	}
