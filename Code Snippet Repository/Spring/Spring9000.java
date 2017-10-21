	@Test
	public void jtaTransactionManagerWithCustomJndiLookups() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_NO_TRANSACTION, Status.STATUS_ACTIVE, Status.STATUS_ACTIVE);

		TransactionManager tm = mock(TransactionManager.class);

		JtaTransactionManager ptm = new JtaTransactionManager();
		ptm.setUserTransactionName("jndi-ut");
		ptm.setTransactionManagerName("jndi-tm");
		ExpectedLookupTemplate jndiTemplate = new ExpectedLookupTemplate();
		jndiTemplate.addObject("jndi-ut", ut);
		jndiTemplate.addObject("jndi-tm", tm);
		ptm.setJndiTemplate(jndiTemplate);
		ptm.afterPropertiesSet();

		assertEquals(ut, ptm.getUserTransaction());
		assertEquals(tm, ptm.getTransactionManager());

		TransactionTemplate tt = new TransactionTemplate(ptm);
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// something transactional
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
			}
		});
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		verify(ut).begin();
		verify(ut).commit();
	}
