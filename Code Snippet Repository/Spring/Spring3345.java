	@Test
	public void testJsr250AnnotationsWithShadowedMethods() {
		final Class<?> beanClass = CustomAnnotatedInitDestroyWithShadowedMethodsBean.class;
		final DefaultListableBeanFactory beanFactory = createBeanFactoryAndRegisterBean(beanClass, "customInit",
				"customDestroy");
		final CustomAnnotatedInitDestroyWithShadowedMethodsBean bean = (CustomAnnotatedInitDestroyWithShadowedMethodsBean) beanFactory.getBean(LIFECYCLE_TEST_BEAN);
		assertMethodOrdering(beanClass, "init-methods",
				Arrays.asList("@PostConstruct.afterPropertiesSet", "customInit"), bean.initMethods);
		beanFactory.destroySingletons();
		assertMethodOrdering(beanClass, "destroy-methods", Arrays.asList("@PreDestroy.destroy", "customDestroy"),
				bean.destroyMethods);
	}
