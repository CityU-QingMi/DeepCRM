	@Parameters(name = "")
	public static Object[][] testData() {
		return new Object[][] {
			{ AlwaysFailingBeforeTestClassTestCase.class.getSimpleName(), 1, 0, 0, 1 },
			{ AlwaysFailingAfterTestClassTestCase.class.getSimpleName(), 1, 1, 0, 1 },
			{ AlwaysFailingPrepareTestInstanceTestCase.class.getSimpleName(), 1, 0, 0, 1 },
			{ AlwaysFailingBeforeTestMethodTestCase.class.getSimpleName(), 1, 0, 0, 1 },
			{ AlwaysFailingBeforeTestExecutionTestCase.class.getSimpleName(), 1, 0, 1, 0 },
			{ AlwaysFailingAfterTestExecutionTestCase.class.getSimpleName(), 1, 0, 1, 0 },
			{ AlwaysFailingAfterTestMethodTestCase.class.getSimpleName(), 1, 1, 0, 1 },
			{ FailingBeforeTransactionTestCase.class.getSimpleName(), 1, 0, 0, 1 },
			{ FailingAfterTransactionTestCase.class.getSimpleName(), 1, 1, 0, 1 }
		};
	}
