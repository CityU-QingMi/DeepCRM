	@Test
	public void withAmbiguousTaskSchedulers_andSingleTask_disambiguatedByScheduledTaskRegistrarBean() throws InterruptedException {
		Assume.group(TestGroup.PERFORMANCE);

		ctx = new AnnotationConfigApplicationContext(
				SchedulingEnabled_withAmbiguousTaskSchedulers_andSingleTask_disambiguatedByScheduledTaskRegistrar.class);

		Thread.sleep(100);
		assertThat(ctx.getBean(ThreadAwareWorker.class).executedByThread, startsWith("explicitScheduler2-"));
	}
