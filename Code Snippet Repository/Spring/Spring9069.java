	@Test
	public void transactionAttributeDeclaredOnInterfaceWithEjb3() throws Exception {
		Method getAgeMethod = ITestEjb.class.getMethod("getAge");
		Method getNameMethod = ITestEjb.class.getMethod("getName");

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute getAgeAttr = atas.getTransactionAttribute(getAgeMethod, Ejb3AnnotatedBean3.class);
		assertEquals(TransactionAttribute.PROPAGATION_REQUIRED, getAgeAttr.getPropagationBehavior());
		TransactionAttribute getNameAttr = atas.getTransactionAttribute(getNameMethod, Ejb3AnnotatedBean3.class);
		assertEquals(TransactionAttribute.PROPAGATION_SUPPORTS, getNameAttr.getPropagationBehavior());
	}
