	@Test
	public void determineTransactionManagerWithQualifierAndDefaultName() {
		BeanFactory beanFactory = mock(BeanFactory.class);
		associateTransactionManager(beanFactory, "defaultTransactionManager");
		TransactionInterceptor ti = transactionInterceptorWithTransactionManagerName(
				"defaultTransactionManager", beanFactory);

		PlatformTransactionManager fooTransactionManager =
				associateTransactionManager(beanFactory, "fooTransactionManager");
		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		attribute.setQualifier("fooTransactionManager");

		assertSame(fooTransactionManager, ti.determineTransactionManager(attribute));
	}
