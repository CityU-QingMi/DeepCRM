	@Test
	public void spr11915TransactionManagerAsManualSingleton() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Spr11915Config.class);
		TransactionalTestBean bean = ctx.getBean(TransactionalTestBean.class);
		CallCountingTransactionManager txManager = ctx.getBean("qualifiedTransactionManager", CallCountingTransactionManager.class);

		bean.saveQualifiedFoo();
		assertThat(txManager.begun, equalTo(1));
		assertThat(txManager.commits, equalTo(1));
		assertThat(txManager.rollbacks, equalTo(0));

		bean.saveQualifiedFooWithAttributeAlias();
		assertThat(txManager.begun, equalTo(2));
		assertThat(txManager.commits, equalTo(2));
		assertThat(txManager.rollbacks, equalTo(0));

		ctx.close();
	}
