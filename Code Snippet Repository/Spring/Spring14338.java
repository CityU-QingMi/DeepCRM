	@Test
	public void succeedsWhenSubclassProxyAndScheduledMethodNotPresentOnInterface() throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class, SubclassProxyTxConfig.class, RepoConfigA.class);
		ctx.refresh();

		Thread.sleep(100);  // allow @Scheduled method to be called several times

		MyRepository repository = ctx.getBean(MyRepository.class);
		CallCountingTransactionManager txManager = ctx.getBean(CallCountingTransactionManager.class);
		assertThat("repository is not a proxy", AopUtils.isCglibProxy(repository), equalTo(true));
		assertThat("@Scheduled method never called", repository.getInvocationCount(), greaterThan(0));
		assertThat("no transactions were committed", txManager.commits, greaterThan(0));
	}
