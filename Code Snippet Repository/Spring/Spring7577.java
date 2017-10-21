	@Test
	public void sendWithExecutionException() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		IllegalStateException exception = new IllegalStateException("simulated exception");
		SettableListenableFuture<Void> future = new SettableListenableFuture<>();
		future.setException(exception);

		when(this.connection.send(any())).thenReturn(future);
		this.expected.expect(MessageDeliveryException.class);
		this.expected.expectCause(Matchers.sameInstance(exception));

		this.session.send("/topic/foo", "sample payload".getBytes(StandardCharsets.UTF_8));

		verifyNoMoreInteractions(this.connection);
	}
