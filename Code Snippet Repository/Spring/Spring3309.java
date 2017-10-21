	@Test
	public void importSelectors() {
		DefaultListableBeanFactory beanFactory = spy(new DefaultListableBeanFactory());
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(beanFactory);
		context.register(Config.class);
		context.refresh();
		context.getBean(Config.class);
		InOrder ordered = inOrder(beanFactory);
		ordered.verify(beanFactory).registerBeanDefinition(eq("a"), any());
		ordered.verify(beanFactory).registerBeanDefinition(eq("b"), any());
		ordered.verify(beanFactory).registerBeanDefinition(eq("d"), any());
		ordered.verify(beanFactory).registerBeanDefinition(eq("c"), any());
	}
