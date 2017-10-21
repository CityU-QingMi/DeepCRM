	@Test
	public void testNullableMethodInjectionWithBeanNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(NullableMethodInjectionBean.class));

		NullableMethodInjectionBean bean = (NullableMethodInjectionBean) bf.getBean("annotatedBean");
		assertNull(bean.getTestBean());
		bf.destroySingletons();
	}
