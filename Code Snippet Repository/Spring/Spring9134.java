	@Test
	public void testRuleForRollbackOnChecked() {
		List<RollbackRuleAttribute> list = new LinkedList<>();
		list.add(new RollbackRuleAttribute(IOException.class.getName()));
		RuleBasedTransactionAttribute rta = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, list);

		assertTrue(rta.rollbackOn(new RuntimeException()));
		assertTrue(rta.rollbackOn(new MyRuntimeException("")));
		assertFalse(rta.rollbackOn(new Exception()));
		// Check that default behaviour is overridden
		assertTrue(rta.rollbackOn(new IOException()));
	}
