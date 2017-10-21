	@Test
	public void testCustomAnnotationOptionalMethodResourceInjectionWhenMultipleDependenciesFound() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setAutowiredAnnotationType(MyAutowired.class);
		bpp.setRequiredParameterName("optional");
		bpp.setRequiredParameterValue(false);
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("customBean", new RootBeanDefinition(
				CustomAnnotationOptionalMethodResourceInjectionBean.class));
		TestBean tb1 = new TestBean();
		bf.registerSingleton("testBean1", tb1);
		TestBean tb2 = new TestBean();
		bf.registerSingleton("testBean2", tb2);

		try {
			bf.getBean("customBean");
			fail("Should have thrown UnsatisfiedDependencyException");
		}
		catch (UnsatisfiedDependencyException ex) {
			// expected
			assertSame(CustomAnnotationOptionalMethodResourceInjectionBean.class,
					ex.getInjectionPoint().getMethodParameter().getDeclaringClass());
		}
		bf.destroySingletons();
	}
