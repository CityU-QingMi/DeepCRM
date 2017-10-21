	@Test
	public void receiptReceivedBeforeTaskAdded() throws Exception {
		this.session.afterConnected(this.connection);
		this.session.setTaskScheduler(mock(TaskScheduler.class));

		AtomicReference<Boolean> received = new AtomicReference<>();

		StompHeaders headers = new StompHeaders();
		headers.setDestination("/topic/foo");
		headers.setReceipt("my-receipt");
		Subscription subscription = this.session.subscribe(headers, mock(StompFrameHandler.class));

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.RECEIPT);
		accessor.setReceiptId("my-receipt");
		accessor.setLeaveMutable(true);
		this.session.handleMessage(MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders()));

		subscription.addReceiptTask(() -> received.set(true));

		assertNotNull(received.get());
		assertTrue(received.get());
	}
