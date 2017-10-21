	@Test
	public void transactionAttributeDeclaredOnGroovyClass() throws Exception {
		Method getAgeMethod = ITestBean1.class.getMethod("getAge");
		Method getNameMethod = ITestBean1.class.getMethod("getName");
		Method getMetaClassMethod = GroovyObject.class.getMethod("getMetaClass");

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute getAgeAttr = atas.getTransactionAttribute(getAgeMethod, GroovyTestBean.class);
		assertEquals(TransactionAttribute.PROPAGATION_REQUIRED, getAgeAttr.getPropagationBehavior());
		TransactionAttribute getNameAttr = atas.getTransactionAttribute(getNameMethod, GroovyTestBean.class);
		assertEquals(TransactionAttribute.PROPAGATION_REQUIRED, getNameAttr.getPropagationBehavior());
		assertNull(atas.getTransactionAttribute(getMetaClassMethod, GroovyTestBean.class));
	}
