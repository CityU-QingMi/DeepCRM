	@Test
	public void testSmartObjectFactoryInjectionWithPrototype() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SmartObjectFactoryInjectionBean.class));
		RootBeanDefinition tbd = new RootBeanDefinition(TestBean.class);
		tbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("testBean", tbd);

		SmartObjectFactoryInjectionBean bean = (SmartObjectFactoryInjectionBean) bf.getBean("annotatedBean");
		assertEquals(bf.getBean("testBean"), bean.getTestBean());
		assertEquals(bf.getBean("testBean", "myName"), bean.getTestBean("myName"));
		assertEquals(bf.getBean("testBean"), bean.getOptionalTestBean());
		assertEquals(bf.getBean("testBean"), bean.getOptionalTestBeanWithDefault());
		assertEquals(bf.getBean("testBean"), bean.consumeOptionalTestBean());
		assertEquals(bf.getBean("testBean"), bean.getUniqueTestBean());
		assertEquals(bf.getBean("testBean"), bean.getUniqueTestBeanWithDefault());
		assertEquals(bf.getBean("testBean"), bean.consumeUniqueTestBean());
		bf.destroySingletons();
	}
