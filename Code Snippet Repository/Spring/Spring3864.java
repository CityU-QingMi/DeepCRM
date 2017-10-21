	@Test
	public void withExplicitScheduler() throws InterruptedException {
		Assume.group(TestGroup.PERFORMANCE);

		ctx = new AnnotationConfigApplicationContext(ExplicitSchedulerConfig.class);

		Thread.sleep(100);
		assertThat(ctx.getBean(AtomicInteger.class).get(), greaterThanOrEqualTo(10));
		assertThat(ctx.getBean(ExplicitSchedulerConfig.class).threadName, startsWith("explicitScheduler-"));
		assertTrue(Arrays.asList(ctx.getDefaultListableBeanFactory().getDependentBeans("myTaskScheduler")).contains(
				TaskManagementConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME));
	}
