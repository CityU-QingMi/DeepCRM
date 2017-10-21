	@Test
	public void testProviderOfOptionalFieldInjectionWithBeanAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ProviderOfOptionalFieldInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

		ProviderOfOptionalFieldInjectionBean bean = (ProviderOfOptionalFieldInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.getTestBean().isPresent());
		assertSame(bf.getBean("testBean"), bean.getTestBean().get());
		bf.destroySingletons();
	}
