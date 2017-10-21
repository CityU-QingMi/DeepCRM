	@Test
	public void propagationMandatoryFailsInCaseOfNoExistingTransaction() {
		MockUOWManager manager = new MockUOWManager();
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_MANDATORY);

		try {
			ptm.execute(definition, new TransactionCallback<String>() {
				@Override
				public String doInTransaction(TransactionStatus status) {
					return "result";
				}
			});
			fail("Should have thrown IllegalTransactionStateException");
		}
		catch (IllegalTransactionStateException ex) {
			// expected
		}
	}
