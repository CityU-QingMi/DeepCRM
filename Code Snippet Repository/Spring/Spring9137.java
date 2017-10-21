	@Test
	public void testRollbackNever() {
		List<RollbackRuleAttribute> list = new LinkedList<>();
		list.add(new NoRollbackRuleAttribute("Throwable"));
		RuleBasedTransactionAttribute rta = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, list);

		assertFalse(rta.rollbackOn(new Throwable()));
		assertFalse(rta.rollbackOn(new RuntimeException()));
		assertFalse(rta.rollbackOn(new MyRuntimeException("")));
		assertFalse(rta.rollbackOn(new Exception()));
		assertFalse(rta.rollbackOn(new IOException()));
	}
