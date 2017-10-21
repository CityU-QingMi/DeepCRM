	@Test
	public void testBeanPostProcessorWithWrappedObjectAndDestroyMethod() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(BeanWithDestroyMethod.class);
		bd.setDestroyMethodName("close");
		lbf.registerBeanDefinition("test", bd);
		lbf.addBeanPostProcessor(new BeanPostProcessor() {
			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) {
				return new TestBean();
			}
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) {
				return bean;
			}
		});
		BeanWithDestroyMethod.closeCount = 0;
		lbf.preInstantiateSingletons();
		lbf.destroySingletons();
		assertEquals("Destroy methods invoked", 1, BeanWithDestroyMethod.closeCount);
	}
