	@Test
	public void testSmartObjectFactoryInjectionWithTargetNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SmartObjectFactoryInjectionBean.class));

		SmartObjectFactoryInjectionBean bean = (SmartObjectFactoryInjectionBean) bf.getBean("annotatedBean");
		try {
			bean.getTestBean();
			fail("Should have thrown NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException ex) {
			// expected
		}
		assertNull(bean.getOptionalTestBean());
		assertNull(bean.consumeOptionalTestBean());
		assertEquals(new TestBean("default"), bean.getOptionalTestBeanWithDefault());
		assertEquals(new TestBean("default"), bean.getUniqueTestBeanWithDefault());
		assertNull(bean.getUniqueTestBean());
		assertNull(bean.consumeUniqueTestBean());
		bf.destroySingletons();
	}
