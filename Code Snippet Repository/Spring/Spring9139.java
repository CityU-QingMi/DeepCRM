	@Test
	public void testConflictingRulesToDetermineExactContract() {
		List<RollbackRuleAttribute> list = new LinkedList<>();
		list.add(new NoRollbackRuleAttribute(MyBusinessWarningException.class));
		list.add(new RollbackRuleAttribute(MyBusinessException.class));
		RuleBasedTransactionAttribute rta = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, list);

		assertTrue(rta.rollbackOn(new MyBusinessException()));
		assertFalse(rta.rollbackOn(new MyBusinessWarningException()));
	}
