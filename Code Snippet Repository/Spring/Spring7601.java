		public void expectMessages(MessageExchange... messageExchanges) throws InterruptedException {
			List<MessageExchange> expectedMessages =
					new ArrayList<>(Arrays.<MessageExchange>asList(messageExchanges));
			while (expectedMessages.size() > 0) {
				Message<?> message = this.queue.poll(10000, TimeUnit.MILLISECONDS);
				assertNotNull("Timed out waiting for messages, expected [" + expectedMessages + "]", message);
				MessageExchange match = findMatch(expectedMessages, message);
				assertNotNull("Unexpected message=" + message + ", expected [" + expectedMessages + "]", match);
				expectedMessages.remove(match);
			}
		}
