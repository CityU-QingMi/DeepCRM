	@Test
	public void testPostProcessorWithNullBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		RootBeanDefinition rbd = new RootBeanDefinition(NullFactory.class);
		rbd.setFactoryMethodName("create");
		bf.registerBeanDefinition("bean", rbd);

		assertEquals("null", bf.getBean("bean").toString());
		bf.destroySingletons();
	}
