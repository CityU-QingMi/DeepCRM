	@Test
	public void withInitiallyDelayedFixedRateTask() throws InterruptedException {
		Assume.group(TestGroup.PERFORMANCE);

		ctx = new AnnotationConfigApplicationContext(FixedRateTaskConfig_withInitialDelay.class);

		Thread.sleep(1950);
		AtomicInteger counter = ctx.getBean(AtomicInteger.class);

		// The @Scheduled method should have been called at least once but
		// not more times than the delay allows.
		assertThat(counter.get(), both(greaterThan(0)).and(lessThanOrEqualTo(10)));
	}
