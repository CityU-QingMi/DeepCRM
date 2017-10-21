	@Test
	public void succeedsWhenJdkProxyAndScheduledMethodIsPresentOnInterface() throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class, JdkProxyTxConfig.class, RepoConfigB.class);
		ctx.refresh();

		Thread.sleep(100);  // allow @Scheduled method to be called several times

		MyRepositoryWithScheduledMethod repository = ctx.getBean(MyRepositoryWithScheduledMethod.class);
		CallCountingTransactionManager txManager = ctx.getBean(CallCountingTransactionManager.class);
		assertThat("repository is not a proxy", AopUtils.isJdkDynamicProxy(repository), is(true));
		assertThat("@Scheduled method never called", repository.getInvocationCount(), greaterThan(0));
		assertThat("no transactions were committed", txManager.commits, greaterThan(0));
	}
