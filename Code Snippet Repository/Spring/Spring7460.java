	@Test
	public void sessionCompletedIsIdempotent() {
		Runnable callback1 = Mockito.mock(Runnable.class);
		this.simpAttributes.registerDestructionCallback("name1", callback1);

		this.simpAttributes.sessionCompleted();
		this.simpAttributes.sessionCompleted();
		this.simpAttributes.sessionCompleted();

		verify(callback1, times(1)).run();
	}
