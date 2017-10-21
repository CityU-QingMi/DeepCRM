	private void test(String useCase, Class<?> testClass, Callback callback) throws Exception {
		TestContextManager testContextManager = new TestContextManager(testClass);
		assertEquals("Registered TestExecutionListeners", 2, testContextManager.getTestExecutionListeners().size());

		try {
			Method testMethod = getClass().getMethod("toString");
			callback.invoke(testContextManager, testClass, testMethod);
			fail("should have thrown an AssertionError");
		}
		catch (AssertionError err) {
			// 'after' callbacks are reversed, so 2 comes before 1.
			assertEquals(useCase + "-2", err.getMessage());
			Throwable[] suppressed = err.getSuppressed();
			assertEquals(1, suppressed.length);
			assertEquals(useCase + "-1", suppressed[0].getMessage());
		}
	}
