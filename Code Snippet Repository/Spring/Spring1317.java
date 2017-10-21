	@Test
	public void testSmartObjectFactoryInjectionWithTargetPrimary() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SmartObjectFactoryInjectionBean.class));
		RootBeanDefinition tb1 = new RootBeanDefinition(TestBean.class);
		tb1.setPrimary(true);
		bf.registerBeanDefinition("testBean1", tb1);
		RootBeanDefinition tb2 = new RootBeanDefinition(TestBean.class);
		tb2.setLazyInit(true);
		bf.registerBeanDefinition("testBean2", tb2);

		SmartObjectFactoryInjectionBean bean = (SmartObjectFactoryInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("testBean1"), bean.getTestBean());
		assertSame(bf.getBean("testBean1"), bean.getOptionalTestBean());
		assertSame(bf.getBean("testBean1"), bean.consumeOptionalTestBean());
		assertSame(bf.getBean("testBean1"), bean.getUniqueTestBean());
		assertSame(bf.getBean("testBean1"), bean.consumeUniqueTestBean());
		assertFalse(bf.containsSingleton("testBean2"));
		bf.destroySingletons();
	}
