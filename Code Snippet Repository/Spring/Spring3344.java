	@Test
	public void testJsr250Annotations() {
		final Class<?> beanClass = CustomAnnotatedInitDestroyBean.class;
		final DefaultListableBeanFactory beanFactory = createBeanFactoryAndRegisterBean(beanClass, "customInit",
				"customDestroy");
		final CustomAnnotatedInitDestroyBean bean = (CustomAnnotatedInitDestroyBean) beanFactory.getBean(LIFECYCLE_TEST_BEAN);
		assertMethodOrdering(beanClass, "init-methods", Arrays.asList("postConstruct", "afterPropertiesSet",
				"customInit"), bean.initMethods);
		beanFactory.destroySingletons();
		assertMethodOrdering(beanClass, "destroy-methods", Arrays.asList("preDestroy", "destroy", "customDestroy"),
				bean.destroyMethods);
	}
