	@Test(expected = UnsatisfiedDependencyException.class)
	public void testSingleConstructorInjectionWithMissingDependency() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SingleConstructorCollectionInjectionBean.class));

		bf.getBean("annotatedBean");
	}
