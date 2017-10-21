	@Test
	public void testOrderedResourceInjection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalResourceInjectionBean.class));
		TestBean tb = new TestBean();
		bf.registerSingleton("testBean", tb);
		IndexedTestBean itb = new IndexedTestBean();
		bf.registerSingleton("indexedTestBean", itb);
		OrderedNestedTestBean ntb1 = new OrderedNestedTestBean();
		ntb1.setOrder(2);
		bf.registerSingleton("nestedTestBean1", ntb1);
		OrderedNestedTestBean ntb2 = new OrderedNestedTestBean();
		ntb2.setOrder(1);
		bf.registerSingleton("nestedTestBean2", ntb2);

		OptionalResourceInjectionBean bean = (OptionalResourceInjectionBean) bf.getBean("annotatedBean");
		assertSame(tb, bean.getTestBean());
		assertSame(tb, bean.getTestBean2());
		assertSame(tb, bean.getTestBean3());
		assertSame(tb, bean.getTestBean4());
		assertSame(itb, bean.getIndexedTestBean());
		assertEquals(2, bean.getNestedTestBeans().length);
		assertSame(ntb2, bean.getNestedTestBeans()[0]);
		assertSame(ntb1, bean.getNestedTestBeans()[1]);
		assertEquals(2, bean.nestedTestBeansField.length);
		assertSame(ntb2, bean.nestedTestBeansField[0]);
		assertSame(ntb1, bean.nestedTestBeansField[1]);
		bf.destroySingletons();
	}
