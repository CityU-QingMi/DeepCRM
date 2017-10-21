	@Test
	public void testNullableFieldInjectionWithBeanNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(NullableFieldInjectionBean.class));

		NullableFieldInjectionBean bean = (NullableFieldInjectionBean) bf.getBean("annotatedBean");
		assertNull(bean.getTestBean());
		bf.destroySingletons();
	}
