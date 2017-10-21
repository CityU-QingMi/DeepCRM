	@Test
	public void getHandlerWithBeanFactory() {

		@SuppressWarnings("resource")
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		BeanCreatingHandlerProvider<EchoHandler> provider =
				new BeanCreatingHandlerProvider<>(EchoHandler.class);
		provider.setBeanFactory(context.getBeanFactory());

		assertNotNull(provider.getHandler());
	}
