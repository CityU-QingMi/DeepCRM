	@Test
	public void uowManagerFoundInJndi() {
		MockUOWManager manager = new MockUOWManager();
		ExpectedLookupTemplate jndiTemplate =
				new ExpectedLookupTemplate(WebSphereUowTransactionManager.DEFAULT_UOW_MANAGER_NAME, manager);
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager();
		ptm.setJndiTemplate(jndiTemplate);
		ptm.afterPropertiesSet();

		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		assertEquals("result", ptm.execute(definition, new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				return "result";
			}
		}));

		assertEquals(UOWManager.UOW_TYPE_GLOBAL_TRANSACTION, manager.getUOWType());
		assertFalse(manager.getJoined());
		assertFalse(manager.getRollbackOnly());
	}
