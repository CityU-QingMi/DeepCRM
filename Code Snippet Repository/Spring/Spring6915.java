	@Test
	public void testRecoveryInterval() {
		Object testBackOff = context.getBean("testBackOff");
		BackOff backOff1 = getBackOff("listener1");
		BackOff backOff2 = getBackOff("listener2");
		long recoveryInterval3 = getRecoveryInterval(DefaultMessageListenerContainer.class.getName() + "#0");

		assertSame(testBackOff, backOff1);
		assertSame(testBackOff, backOff2);
		assertEquals(DefaultMessageListenerContainer.DEFAULT_RECOVERY_INTERVAL, recoveryInterval3);
	}
