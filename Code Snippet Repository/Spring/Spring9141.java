	@Test
	public void testValidPropagationCodeAndIsolationCodeAndRollbackRules1() {
		TransactionAttributeEditor pe = new TransactionAttributeEditor();
		pe.setAsText("PROPAGATION_MANDATORY,ISOLATION_REPEATABLE_READ,timeout_10,-IOException,+MyRuntimeException");
		TransactionAttribute ta = (TransactionAttribute) pe.getValue();
		assertNotNull(ta);
		assertEquals(ta.getPropagationBehavior(), TransactionDefinition.PROPAGATION_MANDATORY);
		assertEquals(ta.getIsolationLevel(), TransactionDefinition.ISOLATION_REPEATABLE_READ);
		assertEquals(ta.getTimeout(), 10);
		assertFalse(ta.isReadOnly());
		assertTrue(ta.rollbackOn(new RuntimeException()));
		assertFalse(ta.rollbackOn(new Exception()));
		// Check for our bizarre customized rollback rules
		assertTrue(ta.rollbackOn(new IOException()));
		assertTrue(!ta.rollbackOn(new MyRuntimeException("")));
	}
