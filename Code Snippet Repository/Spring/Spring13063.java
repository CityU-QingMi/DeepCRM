	@Test
	public void onTimeoutAfterHandlerInitialized() throws Exception  {
		this.emitter.initialize(this.handler);

		ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.handler).onTimeout(captor.capture());
		verify(this.handler).onCompletion(any());

		Runnable runnable = mock(Runnable.class);
		this.emitter.onTimeout(runnable);

		assertNotNull(captor.getValue());
		captor.getValue().run();
		verify(runnable).run();
	}
