	@Test
	public void testRuleBasedTransactionAttributeToString() {
		RuleBasedTransactionAttribute source = new RuleBasedTransactionAttribute();
		source.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
		source.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		source.setTimeout(10);
		source.setReadOnly(true);
		source.getRollbackRules().add(new RollbackRuleAttribute("IllegalArgumentException"));
		source.getRollbackRules().add(new NoRollbackRuleAttribute("IllegalStateException"));

		TransactionAttributeEditor pe = new TransactionAttributeEditor();
		pe.setAsText(source.toString());
		TransactionAttribute ta = (TransactionAttribute) pe.getValue();
		assertEquals(ta, source);
		assertEquals(ta.getPropagationBehavior(), TransactionDefinition.PROPAGATION_SUPPORTS);
		assertEquals(ta.getIsolationLevel(), TransactionDefinition.ISOLATION_REPEATABLE_READ);
		assertEquals(ta.getTimeout(), 10);
		assertTrue(ta.isReadOnly());
		assertTrue(ta.rollbackOn(new IllegalArgumentException()));
		assertFalse(ta.rollbackOn(new IllegalStateException()));

		source.getRollbackRules().clear();
		assertNotSame(ta, source);
		source.getRollbackRules().add(new RollbackRuleAttribute("IllegalArgumentException"));
		source.getRollbackRules().add(new NoRollbackRuleAttribute("IllegalStateException"));
		assertEquals(ta, source);
	}
