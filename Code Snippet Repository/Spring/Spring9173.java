	@Test
	public void uowManagerAndUserTransactionFoundInJndi() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn( Status.STATUS_NO_TRANSACTION, Status.STATUS_ACTIVE, Status.STATUS_ACTIVE);

		MockUOWManager manager = new MockUOWManager();
		ExpectedLookupTemplate jndiTemplate = new ExpectedLookupTemplate();
		jndiTemplate.addObject(WebSphereUowTransactionManager.DEFAULT_USER_TRANSACTION_NAME, ut);
		jndiTemplate.addObject(WebSphereUowTransactionManager.DEFAULT_UOW_MANAGER_NAME, manager);
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager();
		ptm.setJndiTemplate(jndiTemplate);
		ptm.afterPropertiesSet();

		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus ts = ptm.getTransaction(definition);
		ptm.commit(ts);
		assertEquals("result", ptm.execute(definition, new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				return "result";
			}
		}));

		assertEquals(UOWManager.UOW_TYPE_GLOBAL_TRANSACTION, manager.getUOWType());
		assertFalse(manager.getJoined());
		assertFalse(manager.getRollbackOnly());
		verify(ut).begin();
		verify(ut).commit();
	}
