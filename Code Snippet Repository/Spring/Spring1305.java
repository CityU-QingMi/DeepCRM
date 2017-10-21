	@Test
	public void testSelfReferenceCollectionWithOther() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SelfInjectionCollectionBean.class));
		bf.registerBeanDefinition("annotatedBean2", new RootBeanDefinition(SelfInjectionCollectionBean.class));

		SelfInjectionCollectionBean bean = (SelfInjectionCollectionBean) bf.getBean("annotatedBean");
		SelfInjectionCollectionBean bean2 = (SelfInjectionCollectionBean) bf.getBean("annotatedBean2");
		assertSame(bean2, bean.reference);
		assertSame(1, bean2.referenceCollection.size());
		assertSame(bean2, bean.referenceCollection.get(0));
	}
