	@Test
	public void transactionAttributeDeclaredOnCglibClassMethod() throws Exception {
		Method classMethod = ITestBean1.class.getMethod("getAge");
		TestBean1 tb = new TestBean1();
		ProxyFactory pf = new ProxyFactory(tb);
		pf.setProxyTargetClass(true);
		Object proxy = pf.getProxy();

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute actual = atas.getTransactionAttribute(classMethod, proxy.getClass());

		RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();
		rbta.getRollbackRules().add(new RollbackRuleAttribute(Exception.class));
		assertEquals(rbta.getRollbackRules(), ((RuleBasedTransactionAttribute) actual).getRollbackRules());
	}
