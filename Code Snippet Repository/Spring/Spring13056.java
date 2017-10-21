	@Test
	public void onCompletionAfterHandlerInitialized() throws Exception  {
		this.emitter.initialize(this.handler);

		ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.handler).onTimeout(any());
		verify(this.handler).onCompletion(captor.capture());

		Runnable runnable = mock(Runnable.class);
		this.emitter.onCompletion(runnable);

		assertNotNull(captor.getValue());
		captor.getValue().run();
		verify(runnable).run();
	}
