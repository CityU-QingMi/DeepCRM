	@Test
	public void applyBackOffRetry() {
		BackOff backOff = mock(BackOff.class);
		BackOffExecution execution = mock(BackOffExecution.class);
		given(execution.nextBackOff()).willReturn(50L, BackOffExecution.STOP);
		given(backOff.start()).willReturn(execution);

		DefaultMessageListenerContainer container = createContainer(createFailingContainerFactory());
		container.setBackOff(backOff);
		container.start();
		container.refreshConnectionUntilSuccessful();

		assertEquals(false, container.isRunning());
		verify(backOff).start();
		verify(execution, times(2)).nextBackOff();
	}
