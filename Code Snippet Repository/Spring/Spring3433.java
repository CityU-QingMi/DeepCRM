	@Test
	public void beanPostProcessorPublishesEvents() {
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("listener", new RootBeanDefinition(BeanThatListens.class));
		context.registerBeanDefinition("messageSource", new RootBeanDefinition(StaticMessageSource.class));
		context.registerBeanDefinition("postProcessor", new RootBeanDefinition(EventPublishingBeanPostProcessor.class));
		context.refresh();

		context.publishEvent(new MyEvent(this));
		BeanThatListens listener = context.getBean(BeanThatListens.class);
		assertEquals(4, listener.getEventCount());

		context.close();
	}
