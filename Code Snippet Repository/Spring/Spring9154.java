	@Test
	public void determineTransactionManagerWithBeanNameSeveralTimes() {
		BeanFactory beanFactory = mock(BeanFactory.class);
		TransactionInterceptor ti = transactionInterceptorWithTransactionManagerName(
				"fooTransactionManager", beanFactory);

		PlatformTransactionManager txManager = 	associateTransactionManager(beanFactory, "fooTransactionManager");

		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		PlatformTransactionManager actual = ti.determineTransactionManager(attribute);
		assertSame(txManager, actual);

		// Call again, should be cached
		PlatformTransactionManager actual2 = ti.determineTransactionManager(attribute);
		assertSame(txManager, actual2);
		verify(beanFactory, times(1)).getBean("fooTransactionManager", PlatformTransactionManager.class);
	}
