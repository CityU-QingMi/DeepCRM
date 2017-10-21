	@Test
	public void testCustomAnnotationRequiredFieldResourceInjectionFailsWhenNoDependencyFound() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setAutowiredAnnotationType(MyAutowired.class);
		bpp.setRequiredParameterName("optional");
		bpp.setRequiredParameterValue(false);
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("customBean", new RootBeanDefinition(
				CustomAnnotationRequiredFieldResourceInjectionBean.class));

		try {
			bf.getBean("customBean");
			fail("Should have thrown UnsatisfiedDependencyException");
		}
		catch (UnsatisfiedDependencyException ex) {
			// expected
			assertSame(CustomAnnotationRequiredFieldResourceInjectionBean.class,
					ex.getInjectionPoint().getField().getDeclaringClass());
		}
		bf.destroySingletons();
	}
