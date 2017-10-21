	@Test(expected = BeanCreationException.class)
	public void cronTaskWithInvalidZone() throws InterruptedException {
		Assume.group(TestGroup.LONG_RUNNING);

		BeanDefinition processorDefinition = new RootBeanDefinition(ScheduledAnnotationBeanPostProcessor.class);
		BeanDefinition targetDefinition = new RootBeanDefinition(CronWithInvalidTimezoneTestBean.class);
		context.registerBeanDefinition("postProcessor", processorDefinition);
		context.registerBeanDefinition("target", targetDefinition);
		context.refresh();
		Thread.sleep(10000);
	}
