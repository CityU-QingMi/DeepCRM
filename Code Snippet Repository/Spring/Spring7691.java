	@Test
	public void sendWithExecutor() throws Exception {
		BeforeHandleInterceptor interceptor = new BeforeHandleInterceptor();
		TaskExecutor executor = mock(TaskExecutor.class);
		ExecutorSubscribableChannel testChannel = new ExecutorSubscribableChannel(executor);
		testChannel.addInterceptor(interceptor);
		testChannel.subscribe(this.handler);
		testChannel.send(this.message);
		verify(executor).execute(this.runnableCaptor.capture());
		verify(this.handler, never()).handleMessage(this.message);
		this.runnableCaptor.getValue().run();
		verify(this.handler).handleMessage(this.message);
		assertEquals(1, interceptor.getCounter().get());
		assertTrue(interceptor.wasAfterHandledInvoked());
	}
