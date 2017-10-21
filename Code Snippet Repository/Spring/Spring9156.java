	private TransactionInterceptor createTransactionInterceptor(BeanFactory beanFactory,
			String transactionManagerName, PlatformTransactionManager transactionManager) {
		TransactionInterceptor ti = new TransactionInterceptor();
		if (beanFactory != null) {
			ti.setBeanFactory(beanFactory);
		}
		if (transactionManagerName != null) {
			ti.setTransactionManagerBeanName(transactionManagerName);

		}
		if (transactionManager != null) {
			ti.setTransactionManager(transactionManager);
		}
		ti.setTransactionAttributeSource(new NameMatchTransactionAttributeSource());
		ti.afterPropertiesSet();
		return ti;
	}
