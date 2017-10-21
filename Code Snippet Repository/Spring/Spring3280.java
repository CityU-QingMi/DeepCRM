	@Test
	public void testCircularDependency() {
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(bpp);
		beanFactory.registerBeanDefinition("configClass1", new RootBeanDefinition(A.class));
		beanFactory.registerBeanDefinition("configClass2", new RootBeanDefinition(AStrich.class));
		new ConfigurationClassPostProcessor().postProcessBeanFactory(beanFactory);
		try {
			beanFactory.preInstantiateSingletons();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMessage().contains("Circular reference"));
		}
	}
