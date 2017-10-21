	@Test
	public void testSmartObjectFactoryInjectionWithSingletonTarget() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SmartObjectFactoryInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

		SmartObjectFactoryInjectionBean bean = (SmartObjectFactoryInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("testBean"), bean.getTestBean());
		assertSame(bf.getBean("testBean"), bean.getOptionalTestBean());
		assertSame(bf.getBean("testBean"), bean.getOptionalTestBeanWithDefault());
		assertEquals(bf.getBean("testBean"), bean.consumeOptionalTestBean());
		assertSame(bf.getBean("testBean"), bean.getUniqueTestBean());
		assertSame(bf.getBean("testBean"), bean.getUniqueTestBeanWithDefault());
		assertEquals(bf.getBean("testBean"), bean.consumeUniqueTestBean());
		bf.destroySingletons();
	}
