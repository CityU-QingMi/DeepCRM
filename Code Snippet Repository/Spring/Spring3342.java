	@Test
	public void testInitializingDisposableInterfaces() {
		final Class<?> beanClass = CustomInitializingDisposableBean.class;
		final DefaultListableBeanFactory beanFactory = createBeanFactoryAndRegisterBean(beanClass, "customInit",
				"customDestroy");
		final CustomInitializingDisposableBean bean = (CustomInitializingDisposableBean) beanFactory.getBean(LIFECYCLE_TEST_BEAN);
		assertMethodOrdering(beanClass, "init-methods", Arrays.asList("afterPropertiesSet", "customInit"),
				bean.initMethods);
		beanFactory.destroySingletons();
		assertMethodOrdering(beanClass, "destroy-methods", Arrays.asList("destroy", "customDestroy"),
				bean.destroyMethods);
	}
