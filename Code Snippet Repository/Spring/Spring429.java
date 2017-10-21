	@Test
	public void testCanGetStatsViaMixin() {
		ThreadLocalTargetSourceStats stats = (ThreadLocalTargetSourceStats) beanFactory.getBean("apartment");
		// +1 because creating target for stats call counts
		assertEquals(1, stats.getInvocationCount());
		SideEffectBean apartment = (SideEffectBean) beanFactory.getBean("apartment");
		apartment.doWork();
		// +1 again
		assertEquals(3, stats.getInvocationCount());
		// + 1 for states call!
		assertEquals(3, stats.getHitCount());
		apartment.doWork();
		assertEquals(6, stats.getInvocationCount());
		assertEquals(6, stats.getHitCount());
		// Only one thread so only one object can have been bound
		assertEquals(1, stats.getObjectCount());
	}
