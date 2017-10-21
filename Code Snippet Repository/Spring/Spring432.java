	@Test
	public void testRefreshCheckWithNonRefresh() throws Exception {
		CountingRefreshableTargetSource ts = new CountingRefreshableTargetSource();
		ts.setRefreshCheckDelay(0);

		Object a = ts.getTarget();
		Thread.sleep(1);
		Object b = ts.getTarget();

		assertEquals("Should be one call to freshTarget to get initial target", 1, ts.getCallCount());
		assertSame("Returned objects should be the same - no refresh should occur", a, b);
	}
