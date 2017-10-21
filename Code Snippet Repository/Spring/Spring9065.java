	@Test
	public void customClassAttributeWithReadOnlyOverrideOnInterface() throws Exception {
		Method method = TestInterface9.class.getMethod("getAge");

		Transactional annotation = AnnotationUtils.findAnnotation(method, Transactional.class);
		assertNull("AnnotationUtils.findAnnotation should not find @Transactional for TestBean9.getAge()", annotation);
		annotation = AnnotationUtils.findAnnotation(TestBean9.class, Transactional.class);
		assertNotNull("AnnotationUtils.findAnnotation failed to find @Transactional for TestBean9", annotation);

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute actual = atas.getTransactionAttribute(method, TestBean9.class);
		assertNotNull("Failed to retrieve TransactionAttribute for TestBean9.getAge()", actual);

		RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();
		rbta.getRollbackRules().add(new RollbackRuleAttribute(Exception.class));
		rbta.getRollbackRules().add(new NoRollbackRuleAttribute(IOException.class));
		assertEquals(rbta.getRollbackRules(), ((RuleBasedTransactionAttribute) actual).getRollbackRules());

		assertTrue(actual.isReadOnly());
	}
