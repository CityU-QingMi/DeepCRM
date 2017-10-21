	@Test
	public void testValidPropagationCodeAndIsolationCodeAndRollbackRules2() {
		TransactionAttributeEditor pe = new TransactionAttributeEditor();
		pe.setAsText("+IOException,readOnly,ISOLATION_READ_COMMITTED,-MyRuntimeException,PROPAGATION_SUPPORTS");
		TransactionAttribute ta = (TransactionAttribute) pe.getValue();
		assertNotNull(ta);
		assertEquals(ta.getPropagationBehavior(), TransactionDefinition.PROPAGATION_SUPPORTS);
		assertEquals(ta.getIsolationLevel(), TransactionDefinition.ISOLATION_READ_COMMITTED);
		assertEquals(ta.getTimeout(), TransactionDefinition.TIMEOUT_DEFAULT);
		assertTrue(ta.isReadOnly());
		assertTrue(ta.rollbackOn(new RuntimeException()));
		assertFalse(ta.rollbackOn(new Exception()));
		// Check for our bizarre customized rollback rules
		assertFalse(ta.rollbackOn(new IOException()));
		assertTrue(ta.rollbackOn(new MyRuntimeException("")));
	}
