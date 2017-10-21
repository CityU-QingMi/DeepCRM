	@Test
	public void applyBackOff() {
		BackOff backOff = mock(BackOff.class);
		BackOffExecution execution = mock(BackOffExecution.class);
		given(execution.nextBackOff()).willReturn(BackOffExecution.STOP);
		given(backOff.start()).willReturn(execution);

		DefaultMessageListenerContainer container = createContainer(createFailingContainerFactory());
		container.setBackOff(backOff);
		container.start();
		assertEquals(true, container.isRunning());

		container.refreshConnectionUntilSuccessful();

		assertEquals(false, container.isRunning());
		verify(backOff).start();
		verify(execution).nextBackOff();
	}
