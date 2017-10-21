	@Test
	public void determineTransactionManagerWithQualifierAndDefault() {
		BeanFactory beanFactory = mock(BeanFactory.class);
		PlatformTransactionManager transactionManager = mock(PlatformTransactionManager.class);
		TransactionInterceptor ti = transactionInterceptorWithTransactionManager(transactionManager, beanFactory);
		PlatformTransactionManager fooTransactionManager =
				associateTransactionManager(beanFactory, "fooTransactionManager");

		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		attribute.setQualifier("fooTransactionManager");

		assertSame(fooTransactionManager, ti.determineTransactionManager(attribute));
	}
