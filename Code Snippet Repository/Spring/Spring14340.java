	@Test
	public void withAspectConfig() throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AspectConfig.class, MyRepositoryWithScheduledMethodImpl.class);
		ctx.refresh();

		Thread.sleep(100);  // allow @Scheduled method to be called several times

		MyRepositoryWithScheduledMethod repository = ctx.getBean(MyRepositoryWithScheduledMethod.class);
		assertThat("repository is not a proxy", AopUtils.isCglibProxy(repository), is(true));
		assertThat("@Scheduled method never called", repository.getInvocationCount(), greaterThan(0));
	}
