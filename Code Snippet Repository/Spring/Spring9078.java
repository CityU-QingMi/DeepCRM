	@Test
	public void transactionAttributeOnTargetClassMethodOverridesAttributeOnInterfaceMethod() throws Exception {
		Method interfaceMethod = ITestBean3.class.getMethod("getAge");
		Method interfaceMethod2 = ITestBean3.class.getMethod("getName");

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute actual = atas.getTransactionAttribute(interfaceMethod, TestBean3.class);
		assertEquals(TransactionAttribute.PROPAGATION_REQUIRES_NEW, actual.getPropagationBehavior());
		assertEquals(TransactionAttribute.ISOLATION_REPEATABLE_READ, actual.getIsolationLevel());
		assertEquals(5, actual.getTimeout());
		assertTrue(actual.isReadOnly());

		RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();
		rbta.getRollbackRules().add(new RollbackRuleAttribute(Exception.class));
		rbta.getRollbackRules().add(new NoRollbackRuleAttribute(IOException.class));
		assertEquals(rbta.getRollbackRules(), ((RuleBasedTransactionAttribute) actual).getRollbackRules());

		TransactionAttribute actual2 = atas.getTransactionAttribute(interfaceMethod2, TestBean3.class);
		assertEquals(TransactionAttribute.PROPAGATION_REQUIRED, actual2.getPropagationBehavior());
	}
