	private MessageHandler createLateReplier(final CountDownLatch latch, final AtomicReference<Throwable> failure) {
		MessageHandler handler = message -> {
			try {
				Thread.sleep(500);
				MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
				replyChannel.send(new GenericMessage<>("response"));
				failure.set(new IllegalStateException("Expected exception"));
			}
			catch (InterruptedException e) {
				failure.set(e);
			}
			catch (MessageDeliveryException ex) {
				String expected = "Reply message received but the receiving thread has exited due to a timeout";
				String actual = ex.getMessage();
				if (!expected.equals(actual)) {
					failure.set(new IllegalStateException(
							"Unexpected error: '" + actual + "'"));
				}
			}
			finally {
				latch.countDown();
			}
		};
		return handler;
	}
