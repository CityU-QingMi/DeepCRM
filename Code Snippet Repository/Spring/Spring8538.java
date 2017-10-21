	@Test
	public void transactionalTestWithoutTransactionManager() throws Exception {
		TransactionalTestExecutionListener listener = new TransactionalTestExecutionListener() {

			protected PlatformTransactionManager getTransactionManager(TestContext testContext, String qualifier) {
				return null;
			}
		};

		Class<? extends Invocable> clazz = TransactionalDeclaredOnClassLocallyTestCase.class;

		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		Invocable instance = clazz.newInstance();
		given(testContext.getTestInstance()).willReturn(instance);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("transactionalTest"));

		assertFalse("callback should not have been invoked", instance.invoked());
		TransactionContextHolder.removeCurrentTransactionContext();

		try {
			listener.beforeTestMethod(testContext);
			fail("Should have thrown an IllegalStateException");
		}
		catch (IllegalStateException e) {
			assertTrue(e.getMessage().startsWith(
				"Failed to retrieve PlatformTransactionManager for @Transactional test for test context"));
		}
	}
