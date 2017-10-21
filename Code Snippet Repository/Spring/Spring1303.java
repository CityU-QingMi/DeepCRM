	@Test
	public void testSelfReferenceWithOther() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SelfInjectionBean.class));
		bf.registerBeanDefinition("annotatedBean2", new RootBeanDefinition(SelfInjectionBean.class));

		SelfInjectionBean bean = (SelfInjectionBean) bf.getBean("annotatedBean");
		SelfInjectionBean bean2 = (SelfInjectionBean) bf.getBean("annotatedBean2");
		assertSame(bean2, bean.reference);
		assertEquals(1, bean.referenceCollection.size());
		assertSame(bean2, bean.referenceCollection.get(0));
	}
