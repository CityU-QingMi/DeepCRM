	@Test
	public void customMethodAttributeWithReadOnlyOverrideOnInterface() throws Exception {
		Method method = TestInterface10.class.getMethod("getAge");

		Transactional annotation = AnnotationUtils.findAnnotation(method, Transactional.class);
		assertNotNull("AnnotationUtils.findAnnotation failed to find @Transactional for TestBean10.getAge()",
				annotation);
		annotation = AnnotationUtils.findAnnotation(TestBean10.class, Transactional.class);
		assertNull("AnnotationUtils.findAnnotation should not find @Transactional for TestBean10", annotation);

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		TransactionAttribute actual = atas.getTransactionAttribute(method, TestBean10.class);
		assertNotNull("Failed to retrieve TransactionAttribute for TestBean10.getAge()", actual);

		RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();
		rbta.getRollbackRules().add(new RollbackRuleAttribute(Exception.class));
		rbta.getRollbackRules().add(new NoRollbackRuleAttribute(IOException.class));
		assertEquals(rbta.getRollbackRules(), ((RuleBasedTransactionAttribute) actual).getRollbackRules());

		assertTrue(actual.isReadOnly());
	}
