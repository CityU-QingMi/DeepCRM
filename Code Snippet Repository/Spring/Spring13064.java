	@Test
	public void onCompletionBeforeHandlerInitialized() throws Exception  {
		Runnable runnable = mock(Runnable.class);
		this.emitter.onCompletion(runnable);
		this.emitter.initialize(this.handler);

		ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.handler).onTimeout(any());
		verify(this.handler).onCompletion(captor.capture());

		assertNotNull(captor.getValue());
		captor.getValue().run();
		verify(runnable).run();
	}
