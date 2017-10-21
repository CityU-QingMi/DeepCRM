	private void assertAfterTestMethodWithTransactionalTestMethod(Class<? extends Invocable> clazz) throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		Invocable instance = clazz.newInstance();
		given(testContext.getTestInstance()).willReturn(instance);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("transactionalTest"));

		given(tm.getTransaction(BDDMockito.any(TransactionDefinition.class))).willReturn(new SimpleTransactionStatus());

		assertFalse("callback should not have been invoked", instance.invoked());
		TransactionContextHolder.removeCurrentTransactionContext();
		listener.beforeTestMethod(testContext);
		assertFalse("callback should not have been invoked", instance.invoked());
		listener.afterTestMethod(testContext);
		assertTrue("callback should have been invoked", instance.invoked());
	}
