	@Test
	public void spr14322FindsOnInterfaceWithCglibProxy() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Spr14322ConfigB.class);
		TransactionalTestInterface bean = ctx.getBean(TransactionalTestInterface.class);
		CallCountingTransactionManager txManager = ctx.getBean(CallCountingTransactionManager.class);

		bean.saveFoo();
		bean.saveBar();
		assertThat(txManager.begun, equalTo(2));
		assertThat(txManager.commits, equalTo(2));
		assertThat(txManager.rollbacks, equalTo(0));

		ctx.close();
	}
