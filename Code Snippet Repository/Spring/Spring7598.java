		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
			for (String topic : this.topics) {
				session.setAutoReceipt(true);
				Subscription subscription = session.subscribe(topic, new StompFrameHandler() {
					@Override
					public Type getPayloadType(StompHeaders headers) {
						return String.class;
					}
					@Override
					public void handleFrame(StompHeaders headers, @Nullable Object payload) {
						received.add((String) payload);
					}
				});
				subscription.addReceiptTask(subscriptionLatch::countDown);
			}
		}
