	@Test
	public void testRuleForCommitOnSubclassOfChecked() {
		List<RollbackRuleAttribute> list = new LinkedList<>();
		// Note that it's important to ensure that we have this as
		// a FQN: otherwise it will match everything!
		list.add(new RollbackRuleAttribute("java.lang.Exception"));
		list.add(new NoRollbackRuleAttribute("IOException"));
		RuleBasedTransactionAttribute rta = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, list);

		assertTrue(rta.rollbackOn(new RuntimeException()));
		assertTrue(rta.rollbackOn(new Exception()));
		// Check that default behaviour is overridden
		assertFalse(rta.rollbackOn(new IOException()));
	}
