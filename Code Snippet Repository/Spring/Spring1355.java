	@Test
	public void testBridgeMethodHandling() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("bean1", new RootBeanDefinition(MyCallable.class));
		bf.registerBeanDefinition("bean2", new RootBeanDefinition(SecondCallable.class));
		bf.registerBeanDefinition("bean3", new RootBeanDefinition(FooBar.class));
		assertNotNull(bf.getBean(FooBar.class));
	}
