	@Test
	public void explicitTxManager() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ExplicitTxManagerConfig.class);
		ctx.refresh();

		FooRepository fooRepository = ctx.getBean(FooRepository.class);
		fooRepository.findAll();

		CallCountingTransactionManager txManager1 = ctx.getBean("txManager1", CallCountingTransactionManager.class);
		assertThat(txManager1.begun, equalTo(1));
		assertThat(txManager1.commits, equalTo(1));
		assertThat(txManager1.rollbacks, equalTo(0));

		CallCountingTransactionManager txManager2 = ctx.getBean("txManager2", CallCountingTransactionManager.class);
		assertThat(txManager2.begun, equalTo(0));
		assertThat(txManager2.commits, equalTo(0));
		assertThat(txManager2.rollbacks, equalTo(0));
	}
