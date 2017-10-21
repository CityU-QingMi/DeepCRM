	@Test
	public void testWithNoRefreshCheck() throws Exception {
		CountingRefreshableTargetSource ts = new CountingRefreshableTargetSource(true);
		ts.setRefreshCheckDelay(-1);

		Object a = ts.getTarget();
		Object b = ts.getTarget();

		assertEquals("Refresh target should only be called once", 1, ts.getCallCount());
		assertSame("Objects should be the same - refresh check delay not elapsed", a, b);
	}
