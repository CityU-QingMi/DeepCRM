	@Test
	public void testRollbackRulesOnMethodCauseRollback() throws Exception {
		BeanFactory bf = getBeanFactory();
		Rollback rb = (Rollback) bf.getBean("rollback");

		CallCountingTransactionManager txMan = (CallCountingTransactionManager) bf.getBean(TXMANAGER_BEAN_NAME);
		OrderedTxCheckAdvisor txc = (OrderedTxCheckAdvisor) bf.getBean("orderedBeforeTransaction");
		assertEquals(0, txc.getCountingBeforeAdvice().getCalls());

		assertEquals(0, txMan.commits);
		rb.echoException(null);
		// Fires only on setters
		assertEquals(0, txc.getCountingBeforeAdvice().getCalls());
		assertEquals("Transaction counts match", 1, txMan.commits);

		assertEquals(0, txMan.rollbacks);
		Exception ex = new Exception();
		try {
			rb.echoException(ex);
		}
		catch (Exception actual) {
			assertEquals(ex, actual);
		}
		assertEquals("Transaction counts match", 1, txMan.rollbacks);
	}
