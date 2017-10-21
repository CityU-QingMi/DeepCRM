	@Test
	public void testSingleConstructorInjectionWithMultipleCandidatesAsOrderedCollection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SingleConstructorCollectionInjectionBean.class));
		TestBean tb = new TestBean();
		bf.registerSingleton("testBean", tb);
		FixedOrder2NestedTestBean ntb1 = new FixedOrder2NestedTestBean();
		bf.registerSingleton("nestedTestBean1", ntb1);
		FixedOrder1NestedTestBean ntb2 = new FixedOrder1NestedTestBean();
		bf.registerSingleton("nestedTestBean2", ntb2);

		SingleConstructorCollectionInjectionBean bean = (SingleConstructorCollectionInjectionBean) bf.getBean("annotatedBean");
		assertSame(tb, bean.getTestBean());
		assertEquals(2, bean.getNestedTestBeans().size());
		assertSame(ntb2, bean.getNestedTestBeans().get(0));
		assertSame(ntb1, bean.getNestedTestBeans().get(1));
		bf.destroySingletons();
	}
