	@Test
	public void testConstructorInjectionWithMap() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(MapConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		TestBean tb1 = new TestBean();
		TestBean tb2 = new TestBean();
		bf.registerSingleton("testBean1", tb1);
		bf.registerSingleton("testBean2", tb1);

		MapConstructorInjectionBean bean = (MapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertEquals(2, bean.getTestBeanMap().size());
		assertTrue(bean.getTestBeanMap().keySet().contains("testBean1"));
		assertTrue(bean.getTestBeanMap().keySet().contains("testBean2"));
		assertTrue(bean.getTestBeanMap().values().contains(tb1));
		assertTrue(bean.getTestBeanMap().values().contains(tb2));

		bean = (MapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertEquals(2, bean.getTestBeanMap().size());
		assertTrue(bean.getTestBeanMap().keySet().contains("testBean1"));
		assertTrue(bean.getTestBeanMap().keySet().contains("testBean2"));
		assertTrue(bean.getTestBeanMap().values().contains(tb1));
		assertTrue(bean.getTestBeanMap().values().contains(tb2));
	}
