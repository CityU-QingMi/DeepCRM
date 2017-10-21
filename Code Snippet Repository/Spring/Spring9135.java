	@Test
	public void testRuleForCommitOnUnchecked() {
		List<RollbackRuleAttribute> list = new LinkedList<>();
		list.add(new NoRollbackRuleAttribute(MyRuntimeException.class.getName()));
		list.add(new RollbackRuleAttribute(IOException.class.getName()));
		RuleBasedTransactionAttribute rta = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, list);

		assertTrue(rta.rollbackOn(new RuntimeException()));
		// Check default behaviour is overridden
		assertFalse(rta.rollbackOn(new MyRuntimeException("")));
		assertFalse(rta.rollbackOn(new Exception()));
		// Check that default behaviour is overridden
		assertTrue(rta.rollbackOn(new IOException()));
	}
