	@Test
	public void testConstructorResourceInjectionWithNoCandidatesAndNoFallback() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ConstructorWithoutFallbackBean.class));

		try {
			bf.getBean("annotatedBean");
			fail("Should have thrown UnsatisfiedDependencyException");
		}
		catch (UnsatisfiedDependencyException ex) {
			// expected
			assertSame(ConstructorWithoutFallbackBean.class, ex.getInjectionPoint().getMethodParameter().getDeclaringClass());
		}
	}
