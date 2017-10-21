	@Test
	public void recoverResetBackOff() {
		BackOff backOff = mock(BackOff.class);
		BackOffExecution execution = mock(BackOffExecution.class);
		given(execution.nextBackOff()).willReturn(50L, 50L, 50L); // 3 attempts max
		given(backOff.start()).willReturn(execution);

		DefaultMessageListenerContainer container = createContainer(createRecoverableContainerFactory(1));
		container.setBackOff(backOff);
		container.start();
		container.refreshConnectionUntilSuccessful();

		assertEquals(true, container.isRunning());
		verify(backOff).start();
		verify(execution, times(1)).nextBackOff(); // only on attempt as the second one lead to a recovery
	}
