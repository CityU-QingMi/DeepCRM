	@Test
	public void testOptionalCollectionResourceInjection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition rbd = new RootBeanDefinition(OptionalCollectionResourceInjectionBean.class);
		rbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", rbd);
		TestBean tb = new TestBean();
		bf.registerSingleton("testBean", tb);
		IndexedTestBean itb = new IndexedTestBean();
		bf.registerSingleton("indexedTestBean", itb);
		NestedTestBean ntb1 = new NestedTestBean();
		bf.registerSingleton("nestedTestBean1", ntb1);
		NestedTestBean ntb2 = new NestedTestBean();
		bf.registerSingleton("nestedTestBean2", ntb2);

		// Two calls to verify that caching doesn't break re-creation.
		OptionalCollectionResourceInjectionBean bean = (OptionalCollectionResourceInjectionBean) bf.getBean("annotatedBean");
		bean = (OptionalCollectionResourceInjectionBean) bf.getBean("annotatedBean");
		assertSame(tb, bean.getTestBean());
		assertSame(tb, bean.getTestBean2());
		assertSame(tb, bean.getTestBean3());
		assertSame(tb, bean.getTestBean4());
		assertSame(itb, bean.getIndexedTestBean());
		assertEquals(2, bean.getNestedTestBeans().size());
		assertSame(ntb1, bean.getNestedTestBeans().get(0));
		assertSame(ntb2, bean.getNestedTestBeans().get(1));
		assertEquals(2, bean.nestedTestBeansSetter.size());
		assertSame(ntb1, bean.nestedTestBeansSetter.get(0));
		assertSame(ntb2, bean.nestedTestBeansSetter.get(1));
		assertEquals(2, bean.nestedTestBeansField.size());
		assertSame(ntb1, bean.nestedTestBeansField.get(0));
		assertSame(ntb2, bean.nestedTestBeansField.get(1));
		bf.destroySingletons();
	}
