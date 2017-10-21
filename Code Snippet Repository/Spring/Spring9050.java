	@Test
	public void transactionTemplateInitialization() {
		TestTransactionManager tm = new TestTransactionManager(false, true);
		TransactionTemplate template = new TransactionTemplate();
		template.setTransactionManager(tm);
		assertTrue("correct transaction manager set", template.getTransactionManager() == tm);

		try {
			template.setPropagationBehaviorName("TIMEOUT_DEFAULT");
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
		template.setPropagationBehaviorName("PROPAGATION_SUPPORTS");
		assertTrue("Correct propagation behavior set", template.getPropagationBehavior() == TransactionDefinition.PROPAGATION_SUPPORTS);

		try {
			template.setPropagationBehavior(999);
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
		template.setPropagationBehavior(TransactionDefinition.PROPAGATION_MANDATORY);
		assertTrue("Correct propagation behavior set", template.getPropagationBehavior() == TransactionDefinition.PROPAGATION_MANDATORY);

		try {
			template.setIsolationLevelName("TIMEOUT_DEFAULT");
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
		template.setIsolationLevelName("ISOLATION_SERIALIZABLE");
		assertTrue("Correct isolation level set", template.getIsolationLevel() == TransactionDefinition.ISOLATION_SERIALIZABLE);

		try {
			template.setIsolationLevel(999);
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
		template.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		assertTrue("Correct isolation level set", template.getIsolationLevel() == TransactionDefinition.ISOLATION_REPEATABLE_READ);
	}
