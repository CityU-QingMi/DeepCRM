	private static Stream<Class<?>> testClasses() {
		// @formatter:off
		return Stream.of(
				AlwaysFailingBeforeTestClassTestCase.class,
				AlwaysFailingAfterTestClassTestCase.class,
				AlwaysFailingPrepareTestInstanceTestCase.class,
				AlwaysFailingBeforeTestMethodTestCase.class,
				AlwaysFailingBeforeTestExecutionTestCase.class,
				AlwaysFailingAfterTestExecutionTestCase.class,
				AlwaysFailingAfterTestMethodTestCase.class,
				FailingBeforeTransactionTestCase.class,
				FailingAfterTransactionTestCase.class);
		// @formatter:on
	}
