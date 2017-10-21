	@Test
	public void testRefreshCheckWithRefresh() throws Exception {
		CountingRefreshableTargetSource ts = new CountingRefreshableTargetSource(true);
		ts.setRefreshCheckDelay(0);

		Object a = ts.getTarget();
		Thread.sleep(100);
		Object b = ts.getTarget();

		assertEquals("Should have called freshTarget twice", 2, ts.getCallCount());
		assertNotSame("Should be different objects", a, b);
	}
