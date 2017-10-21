	@Test
	public void rollbackRules() {
		TransactionInterceptor txInterceptor = (TransactionInterceptor) context.getBean("txRollbackAdvice");
		TransactionAttributeSource txAttrSource = txInterceptor.getTransactionAttributeSource();
		TransactionAttribute txAttr = txAttrSource.getTransactionAttribute(getAgeMethod,ITestBean.class);
		assertTrue("should be configured to rollback on Exception",txAttr.rollbackOn(new Exception()));

		txAttr = txAttrSource.getTransactionAttribute(setAgeMethod, ITestBean.class);
		assertFalse("should not rollback on RuntimeException",txAttr.rollbackOn(new RuntimeException()));
	}
