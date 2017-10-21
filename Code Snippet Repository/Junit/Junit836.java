	@Test
	void doesNotLogAnythingForFilterableRunner(LogRecordListener listener) {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		RunnerTestDescriptorAwareFilter filter = adapter(
			matchMethodDescription(createTestDescription(testClass, "failingTest")));

		resolve(new TestClassRequest(testClass, asList(filter)));

		assertThat(listener.getLogRecords(TestClassRequestResolver.class)).isEmpty();
	}
