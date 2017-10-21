	private void assertAfterTestMethodWithNonTransactionalTestMethod(Class<? extends Invocable> clazz) throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		Invocable instance = clazz.newInstance();
		given(testContext.getTestInstance()).willReturn(instance);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("nonTransactionalTest"));

		assertFalse("callback should not have been invoked", instance.invoked());
		TransactionContextHolder.removeCurrentTransactionContext();
		listener.beforeTestMethod(testContext);
		listener.afterTestMethod(testContext);
		assertFalse("callback should not have been invoked", instance.invoked());
	}
