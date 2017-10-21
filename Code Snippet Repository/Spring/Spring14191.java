		public void awaitMessage(TextMessage expected, long timeToWait) throws InterruptedException {
			TextMessage actual = this.receivedMessages.poll(timeToWait, TimeUnit.MILLISECONDS);
			if (actual != null) {
				assertEquals(expected, actual);
			}
			else if (this.transportError != null) {
				throw new AssertionError("Transport error", this.transportError);
			}
			else {
				fail("Timed out waiting for [" + expected + "]");
			}
		}
