	@Test
	public void propagationNestedFailsInCaseOfExistingTransaction() {
		MockUOWManager manager = new MockUOWManager();
		manager.setUOWStatus(UOWManager.UOW_STATUS_ACTIVE);
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);

		try {
			ptm.execute(definition, new TransactionCallback<String>() {
				@Override
				public String doInTransaction(TransactionStatus status) {
					return "result";
				}
			});
			fail("Should have thrown NestedTransactionNotSupportedException");
		}
		catch (NestedTransactionNotSupportedException ex) {
			// expected
		}
	}
