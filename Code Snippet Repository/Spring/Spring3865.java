	@Test
	public void withExplicitScheduledTaskRegistrar() throws InterruptedException {
		Assume.group(TestGroup.PERFORMANCE);

		ctx = new AnnotationConfigApplicationContext(ExplicitScheduledTaskRegistrarConfig.class);

		Thread.sleep(100);
		assertThat(ctx.getBean(AtomicInteger.class).get(), greaterThanOrEqualTo(10));
		assertThat(ctx.getBean(ExplicitScheduledTaskRegistrarConfig.class).threadName, startsWith("explicitScheduler1"));
	}
