	@Test
	public void implicitTxManager() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ImplicitTxManagerConfig.class);
		ctx.refresh();

		FooRepository fooRepository = ctx.getBean(FooRepository.class);
		fooRepository.findAll();

		CallCountingTransactionManager txManager = ctx.getBean(CallCountingTransactionManager.class);
		assertThat(txManager.begun, equalTo(1));
		assertThat(txManager.commits, equalTo(1));
		assertThat(txManager.rollbacks, equalTo(0));
	}
