	@Test
	public void testRefreshOverTime() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		CountingRefreshableTargetSource ts = new CountingRefreshableTargetSource(true);
		ts.setRefreshCheckDelay(100);

		Object a = ts.getTarget();
		Object b = ts.getTarget();
		assertEquals("Objects should be same", a, b);

		Thread.sleep(50);

		Object c = ts.getTarget();
		assertEquals("A and C should be same", a, c);

		Thread.sleep(60);

		Object d = ts.getTarget();
		assertNotNull("D should not be null", d);
		assertFalse("A and D should not be equal", a.equals(d));

		Object e = ts.getTarget();
		assertEquals("D and E should be equal", d, e);

		Thread.sleep(110);

		Object f = ts.getTarget();
		assertFalse("E and F should be different", e.equals(f));
	}
