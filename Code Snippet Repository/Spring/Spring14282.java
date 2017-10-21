	@Test
	public void testRollbackRulesOnMethodPreventRollback() throws Exception {
		BeanFactory bf = getBeanFactory();
		Rollback rb = (Rollback) bf.getBean("rollback");

		CallCountingTransactionManager txMan = (CallCountingTransactionManager) bf.getBean(TXMANAGER_BEAN_NAME);

		assertEquals(0, txMan.commits);
		// Should NOT roll back on ServletException
		try {
			rb.echoException(new ServletException());
		}
		catch (ServletException ex) {

		}
		assertEquals("Transaction counts match", 1, txMan.commits);
	}
