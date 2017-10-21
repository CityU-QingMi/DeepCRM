	@Test
	public void testDefaultTransactionAttributeToString() {
		DefaultTransactionAttribute source = new DefaultTransactionAttribute();
		source.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
		source.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		source.setTimeout(10);
		source.setReadOnly(true);

		TransactionAttributeEditor pe = new TransactionAttributeEditor();
		pe.setAsText(source.toString());
		TransactionAttribute ta = (TransactionAttribute) pe.getValue();
		assertEquals(ta, source);
		assertEquals(ta.getPropagationBehavior(), TransactionDefinition.PROPAGATION_SUPPORTS);
		assertEquals(ta.getIsolationLevel(), TransactionDefinition.ISOLATION_REPEATABLE_READ);
		assertEquals(ta.getTimeout(), 10);
		assertTrue(ta.isReadOnly());
		assertTrue(ta.rollbackOn(new RuntimeException()));
		assertFalse(ta.rollbackOn(new Exception()));

		source.setTimeout(9);
		assertNotSame(ta, source);
		source.setTimeout(10);
		assertEquals(ta, source);
	}
