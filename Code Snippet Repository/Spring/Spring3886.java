	@Test(expected = BeanCreationException.class)
	public void cronTaskWithMethodValidation() throws InterruptedException {
		BeanDefinition validationDefinition = new RootBeanDefinition(MethodValidationPostProcessor.class);
		BeanDefinition processorDefinition = new RootBeanDefinition(ScheduledAnnotationBeanPostProcessor.class);
		BeanDefinition targetDefinition = new RootBeanDefinition(CronTestBean.class);
		context.registerBeanDefinition("methodValidation", validationDefinition);
		context.registerBeanDefinition("postProcessor", processorDefinition);
		context.registerBeanDefinition("target", targetDefinition);
		context.refresh();
	}
