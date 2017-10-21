	@Parameters(name = "")
	public static Object[] testData() {
		return new Object[] {//
			AlwaysFailingBeforeTestClassSpringRuleTestCase.class.getSimpleName(),//
			AlwaysFailingAfterTestClassSpringRuleTestCase.class.getSimpleName(),//
			AlwaysFailingPrepareTestInstanceSpringRuleTestCase.class.getSimpleName(),//
			AlwaysFailingBeforeTestMethodSpringRuleTestCase.class.getSimpleName(),//
			AlwaysFailingAfterTestMethodSpringRuleTestCase.class.getSimpleName(),//
			FailingBeforeTransactionSpringRuleTestCase.class.getSimpleName(),//
			FailingAfterTransactionSpringRuleTestCase.class.getSimpleName() //
		};
	}
