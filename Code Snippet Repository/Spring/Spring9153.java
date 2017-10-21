	@Override
	protected Object advised(Object target, PlatformTransactionManager ptm, TransactionAttributeSource[] tas) throws Exception {
		TransactionInterceptor ti = new TransactionInterceptor();
		ti.setTransactionManager(ptm);
		ti.setTransactionAttributeSources(tas);

		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(0, ti);
		return pf.getProxy();
	}
