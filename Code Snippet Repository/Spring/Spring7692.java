	@Test
	public void failurePropagates() throws Exception {
		RuntimeException ex = new RuntimeException();
		willThrow(ex).given(this.handler).handleMessage(this.message);
		MessageHandler secondHandler = mock(MessageHandler.class);
		this.channel.subscribe(this.handler);
		this.channel.subscribe(secondHandler);
		try {
			this.channel.send(message);
		}
		catch (MessageDeliveryException actualException) {
			assertThat(actualException.getCause(), equalTo(ex));
		}
		verifyZeroInteractions(secondHandler);
	}
