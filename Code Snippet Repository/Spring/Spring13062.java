	@Test
	public void onTimeoutBeforeHandlerInitialized() throws Exception  {
		Runnable runnable = mock(Runnable.class);
		this.emitter.onTimeout(runnable);
		this.emitter.initialize(this.handler);

		ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.handler).onTimeout(captor.capture());
		verify(this.handler).onCompletion(any());

		assertNotNull(captor.getValue());
		captor.getValue().run();
		verify(runnable).run();
	}
