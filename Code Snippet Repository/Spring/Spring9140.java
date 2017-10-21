	@Test
	public void testValidPropagationCodeOnly() {
		TransactionAttributeEditor pe = new TransactionAttributeEditor();
		pe.setAsText("PROPAGATION_REQUIRED");
		TransactionAttribute ta = (TransactionAttribute) pe.getValue();
		assertTrue(ta != null);
		assertTrue(ta.getPropagationBehavior() == TransactionDefinition.PROPAGATION_REQUIRED);
		assertTrue(ta.getIsolationLevel() == TransactionDefinition.ISOLATION_DEFAULT);
		assertTrue(!ta.isReadOnly());
	}
