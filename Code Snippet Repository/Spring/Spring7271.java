	@Override
	public Subscription subscribe(StompHeaders stompHeaders, StompFrameHandler handler) {
		Assert.hasText(stompHeaders.getDestination(), "Destination header is required");
		Assert.notNull(handler, "StompFrameHandler must not be null");

		String subscriptionId = stompHeaders.getId();
		if (!StringUtils.hasText(subscriptionId)) {
			subscriptionId = String.valueOf(DefaultStompSession.this.subscriptionIndex.getAndIncrement());
			stompHeaders.setId(subscriptionId);
		}
		checkOrAddReceipt(stompHeaders);
		Subscription subscription = new DefaultSubscription(stompHeaders, handler);

		StompHeaderAccessor accessor = createHeaderAccessor(StompCommand.SUBSCRIBE);
		accessor.addNativeHeaders(stompHeaders);
		Message<byte[]> message = createMessage(accessor, EMPTY_PAYLOAD);
		execute(message);

		return subscription;
	}
