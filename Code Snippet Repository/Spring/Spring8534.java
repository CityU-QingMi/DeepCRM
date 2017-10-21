	private void assertBeforeTestMethodWithTransactionalTestMethod(Class<? extends Invocable> clazz, boolean invokedInTx)
			throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		Invocable instance = clazz.newInstance();
		given(testContext.getTestInstance()).willReturn(instance);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("transactionalTest"));

		assertFalse("callback should not have been invoked", instance.invoked());
		TransactionContextHolder.removeCurrentTransactionContext();
		listener.beforeTestMethod(testContext);
		assertEquals(invokedInTx, instance.invoked());
	}
