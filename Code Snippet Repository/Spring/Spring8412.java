	@Test
	public void invokeTestContextManagerFromConcurrentThreads() {
		TestContextManager tcm = new TestContextManager(TestCase.class);

		// Run the actual test several times in order to increase the chance of threads
		// stepping on each others' toes by overwriting the same mutable state in the
		// TestContext.
		IntStream.range(1, 20).forEach(i -> {
			actualMethods.clear();
			// Execute TestExecutionListener in parallel, thereby simulating parallel
			// test method execution.
			stream(TestCase.class.getDeclaredMethods()).parallel().forEach(testMethod -> {
				try {
					tcm.beforeTestClass();
					tcm.beforeTestMethod(testInstance, testMethod);
					// no need to invoke the actual test method
					tcm.afterTestMethod(testInstance, testMethod, null);
					tcm.afterTestClass();
				}
				catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			});
			assertThat(actualMethods, equalTo(expectedMethods));
		});
		assertEquals(0, tcm.getTestContext().attributeNames().length);
	}
