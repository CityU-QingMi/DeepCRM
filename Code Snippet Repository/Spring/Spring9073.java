	@Test
	public void transactionAttributeDeclaredOnInterfaceWithJta() throws Exception {
		Method getAgeMethod = ITestEjb.class.getMethod("getAge");
		Method getNameMethod = ITestEjb.class.getMethod("getName");

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute getAgeAttr = atas.getTransactionAttribute(getAgeMethod, JtaAnnotatedBean3.class);
		assertEquals(TransactionAttribute.PROPAGATION_REQUIRED, getAgeAttr.getPropagationBehavior());
		TransactionAttribute getNameAttr = atas.getTransactionAttribute(getNameMethod, JtaAnnotatedBean3.class);
		assertEquals(TransactionAttribute.PROPAGATION_SUPPORTS, getNameAttr.getPropagationBehavior());
	}
