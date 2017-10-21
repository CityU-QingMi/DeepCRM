		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
			session.subscribe(this.topic, new StompFrameHandler() {
				@Override
				public Type getPayloadType(StompHeaders headers) {
					return String.class;
				}
				@Override
				public void handleFrame(StompHeaders headers, @Nullable Object payload) {
					received.add((String) payload);
				}
			});
			try {
				// Delay send since server processes concurrently
				// Ideally order should be preserved or receipts supported (simple broker)
				Thread.sleep(500);
			}
			catch (InterruptedException ex) {
				logger.error(ex);
			}
			session.send(this.topic, this.payload);
		}
