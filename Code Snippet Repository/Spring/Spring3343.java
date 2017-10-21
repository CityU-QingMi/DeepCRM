	@Test
	public void testInitializingDisposableInterfacesWithShadowedMethods() {
		final Class<?> beanClass = InitializingDisposableWithShadowedMethodsBean.class;
		final DefaultListableBeanFactory beanFactory = createBeanFactoryAndRegisterBean(beanClass,
				"afterPropertiesSet", "destroy");
		final InitializingDisposableWithShadowedMethodsBean bean = (InitializingDisposableWithShadowedMethodsBean) beanFactory.getBean(LIFECYCLE_TEST_BEAN);
		assertMethodOrdering(beanClass, "init-methods", Arrays.asList("InitializingBean.afterPropertiesSet"),
				bean.initMethods);
		beanFactory.destroySingletons();
		assertMethodOrdering(beanClass, "destroy-methods", Arrays.asList("DisposableBean.destroy"), bean.destroyMethods);
	}
