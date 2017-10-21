	@Test
	public void determineTransactionManagerDefaultSeveralTimes() {
		BeanFactory beanFactory = mock(BeanFactory.class);
		TransactionInterceptor ti = simpleTransactionInterceptor(beanFactory);

		PlatformTransactionManager txManager = mock(PlatformTransactionManager.class);
		given(beanFactory.getBean(PlatformTransactionManager.class)).willReturn(txManager);

		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		PlatformTransactionManager actual = ti.determineTransactionManager(attribute);
		assertSame(txManager, actual);

		// Call again, should be cached
		PlatformTransactionManager actual2 = ti.determineTransactionManager(attribute);
		assertSame(txManager, actual2);
		verify(beanFactory, times(1)).getBean(PlatformTransactionManager.class);
	}
