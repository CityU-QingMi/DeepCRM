	@Test
	public void testSelfReferenceCollection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SelfInjectionCollectionBean.class));

		SelfInjectionCollectionBean bean = (SelfInjectionCollectionBean) bf.getBean("annotatedBean");
		assertSame(bean, bean.reference);
		assertNull(bean.referenceCollection);
	}
