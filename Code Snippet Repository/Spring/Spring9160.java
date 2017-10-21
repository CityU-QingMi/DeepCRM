	@Test
	public void determineTransactionManagerWithQualifierUnknown() {
		BeanFactory beanFactory = mock(BeanFactory.class);
		TransactionInterceptor ti = simpleTransactionInterceptor(beanFactory);
		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		attribute.setQualifier("fooTransactionManager");

		thrown.expect(NoSuchBeanDefinitionException.class);
		thrown.expectMessage("'fooTransactionManager'");
		ti.determineTransactionManager(attribute);
	}
