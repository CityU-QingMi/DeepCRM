	@Test
	public void transactionAttributeDeclaredOnClassMethodWithJta() throws Exception {
		Method getAgeMethod = ITestBean1.class.getMethod("getAge");
		Method getNameMethod = ITestBean1.class.getMethod("getName");

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute getAgeAttr = atas.getTransactionAttribute(getAgeMethod, JtaAnnotatedBean1.class);
		assertEquals(TransactionAttribute.PROPAGATION_REQUIRED, getAgeAttr.getPropagationBehavior());
		TransactionAttribute getNameAttr = atas.getTransactionAttribute(getNameMethod, JtaAnnotatedBean1.class);
		assertEquals(TransactionAttribute.PROPAGATION_SUPPORTS, getNameAttr.getPropagationBehavior());
	}
