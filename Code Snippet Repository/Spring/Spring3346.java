	@Test
	public void testAllLifecycleMechanismsAtOnce() {
		final Class<?> beanClass = AllInOneBean.class;
		final DefaultListableBeanFactory beanFactory = createBeanFactoryAndRegisterBean(beanClass,
				"afterPropertiesSet", "destroy");
		final AllInOneBean bean = (AllInOneBean) beanFactory.getBean(LIFECYCLE_TEST_BEAN);
		assertMethodOrdering(beanClass, "init-methods", Arrays.asList("afterPropertiesSet"), bean.initMethods);
		beanFactory.destroySingletons();
		assertMethodOrdering(beanClass, "destroy-methods", Arrays.asList("destroy"), bean.destroyMethods);
	}
