	@Test
	public void testInitDestroyMethods() {
		final Class<?> beanClass = InitDestroyBean.class;
		final DefaultListableBeanFactory beanFactory = createBeanFactoryAndRegisterBean(beanClass,
				"afterPropertiesSet", "destroy");
		final InitDestroyBean bean = (InitDestroyBean) beanFactory.getBean(LIFECYCLE_TEST_BEAN);
		assertMethodOrdering(beanClass, "init-methods", Arrays.asList("afterPropertiesSet"), bean.initMethods);
		beanFactory.destroySingletons();
		assertMethodOrdering(beanClass, "destroy-methods", Arrays.asList("destroy"), bean.destroyMethods);
	}
