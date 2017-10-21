	@Test
	public void determineTransactionManagerWithEmptyQualifierAndDefaultName() {
		BeanFactory beanFactory = mock(BeanFactory.class);
		PlatformTransactionManager defaultTransactionManager
				= associateTransactionManager(beanFactory, "defaultTransactionManager");
		TransactionInterceptor ti = transactionInterceptorWithTransactionManagerName(
				"defaultTransactionManager", beanFactory);

		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		attribute.setQualifier("");

		assertSame(defaultTransactionManager, ti.determineTransactionManager(attribute));
	}
