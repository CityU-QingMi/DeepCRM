	@Test
	@Override
	public void test3IncrementCount2() {
		int count = dao.getCount(TEST_NAME);
		// Expecting count=0 after test2IncrementCount1() since REQUIRED transactions
		// participate in the existing transaction (if present), which in this case is the
		// transaction managed by the TestContext framework which will be rolled back
		// after each test method.
		assertEquals("Expected count=0 after test2IncrementCount1().", 0, count);

		count = dao.incrementCount(TEST_NAME);
		assertEquals("Expected count=1 now.", 1, count);
	}
