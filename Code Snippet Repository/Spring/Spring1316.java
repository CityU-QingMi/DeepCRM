	@Test
	public void testSmartObjectFactoryInjectionWithTargetNotUnique() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SmartObjectFactoryInjectionBean.class));
		bf.registerBeanDefinition("testBean1", new RootBeanDefinition(TestBean.class));
		bf.registerBeanDefinition("testBean2", new RootBeanDefinition(TestBean.class));

		SmartObjectFactoryInjectionBean bean = (SmartObjectFactoryInjectionBean) bf.getBean("annotatedBean");
		try {
			bean.getTestBean();
			fail("Should have thrown NoUniqueBeanDefinitionException");
		}
		catch (NoUniqueBeanDefinitionException ex) {
			// expected
		}
		try {
			bean.getOptionalTestBean();
			fail("Should have thrown NoUniqueBeanDefinitionException");
		}
		catch (NoUniqueBeanDefinitionException ex) {
			// expected
		}
		try {
			bean.consumeOptionalTestBean();
			fail("Should have thrown NoUniqueBeanDefinitionException");
		}
		catch (NoUniqueBeanDefinitionException ex) {
			// expected
		}
		assertNull(bean.getUniqueTestBean());
		assertNull(bean.consumeUniqueTestBean());
		bf.destroySingletons();
	}
