	@Test
	public void determineTransactionManagerWithQualifierSeveralTimes() {
		BeanFactory beanFactory = mock(BeanFactory.class);
		TransactionInterceptor ti = simpleTransactionInterceptor(beanFactory);

		PlatformTransactionManager txManager = associateTransactionManager(beanFactory, "fooTransactionManager");

		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		attribute.setQualifier("fooTransactionManager");
		PlatformTransactionManager actual = ti.determineTransactionManager(attribute);
		assertSame(txManager, actual);

		// Call again, should be cached
		PlatformTransactionManager actual2 = ti.determineTransactionManager(attribute);
		assertSame(txManager, actual2);
		verify(beanFactory, times(1)).containsBean("fooTransactionManager");
		verify(beanFactory, times(1)).getBean("fooTransactionManager", PlatformTransactionManager.class);
	}
