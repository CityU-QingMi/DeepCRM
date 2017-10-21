	@Test
	@TestForIssue(jiraKey = "")
	public void testGetForNullReleasePuts() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.simpleCache(true).expiration().maxIdle(500);
		Configuration ppCfg = cb.build();

		InfinispanRegionFactory regionFactory = mock(InfinispanRegionFactory.class);
		doReturn(ppCfg).when(regionFactory).getPendingPutsCacheConfiguration();
		doAnswer(invocation -> TIME_SERVICE.wallClockTime()).when(regionFactory).nextTimestamp();

		PutFromLoadValidator testee = new PutFromLoadValidator(cache, regionFactory, cm);

		for (int i = 0; i < 100; ++i) {
			try {
				withTx(tm, () -> {
					SharedSessionContractImplementor session = mock (SharedSessionContractImplementor.class);
					testee.registerPendingPut(session, KEY1, 0);
					return null;
				});
				TIME_SERVICE.advance(10);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		String ppName = cm.getCache().getName() + "-" + InfinispanRegionFactory.DEF_PENDING_PUTS_RESOURCE;
		Map ppCache = cm.getCache(ppName, false);
		assertNotNull(ppCache);
		Object pendingPutMap = ppCache.get(KEY1);
		assertNotNull(pendingPutMap);
		int size;
		try {
			Method sizeMethod = pendingPutMap.getClass().getMethod("size");
			sizeMethod.setAccessible(true);
			size = (Integer) sizeMethod.invoke(pendingPutMap);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// some of the pending puts need to be expired by now
		assertTrue(size < 100);
		// but some are still registered
		assertTrue(size > 0);
	}
