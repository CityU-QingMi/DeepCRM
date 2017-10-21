	@Test
	public void sessionCompleted() {
		Runnable callback1 = Mockito.mock(Runnable.class);
		Runnable callback2 = Mockito.mock(Runnable.class);
		this.simpAttributes.registerDestructionCallback("name1", callback1);
		this.simpAttributes.registerDestructionCallback("name2", callback2);

		this.simpAttributes.sessionCompleted();

		verify(callback1, times(1)).run();
		verify(callback2, times(1)).run();
	}
