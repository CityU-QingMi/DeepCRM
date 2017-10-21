	@Parameters(name = "")
	public static Object[] testCases() {
		return new Object[] {//
			AlwaysFailingBeforeTestClassTestCase.class.getSimpleName(),//
			AlwaysFailingAfterTestClassTestCase.class.getSimpleName(),//
			AlwaysFailingPrepareTestInstanceTestCase.class.getSimpleName(),//
			AlwaysFailingBeforeTestMethodTestCase.class.getSimpleName(),//
			AlwaysFailingBeforeTestExecutionTestCase.class.getSimpleName(), //
			AlwaysFailingAfterTestExecutionTestCase.class.getSimpleName(), //
			AlwaysFailingAfterTestMethodTestCase.class.getSimpleName(),//
			FailingBeforeTransactionTestCase.class.getSimpleName(),//
			FailingAfterTransactionTestCase.class.getSimpleName() //
		};
	}
