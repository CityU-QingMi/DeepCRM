	@Test
	@SuppressWarnings("")
	public void completableFutureFailure() {
		Message emptyMessage = (Message) MessageBuilder.withPayload(new byte[0]).build();
		given(this.channel.send(any(Message.class))).willReturn(true);
		given(this.converter.toMessage(any(), any(MessageHeaders.class))).willReturn(emptyMessage);

		CompletableFutureController controller = new CompletableFutureController();
		this.messageHandler.registerHandler(controller);
		this.messageHandler.setDestinationPrefixes(Arrays.asList("/app1", "/app2/"));

		Message<?> message = createMessage("/app1/completable-future");
		this.messageHandler.handleMessage(message);

		controller.future.completeExceptionally(new IllegalStateException());
		assertTrue(controller.exceptionCaught);
	}
