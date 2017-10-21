	protected EntityManagerFactory findNamedEntityManagerFactory(String unitName, @Nullable String requestingBeanName)
			throws NoSuchBeanDefinitionException {

		Assert.state(this.beanFactory != null, "ListableBeanFactory required for EntityManagerFactory bean lookup");

		EntityManagerFactory emf = EntityManagerFactoryUtils.findEntityManagerFactory(this.beanFactory, unitName);
		if (requestingBeanName != null && this.beanFactory instanceof ConfigurableBeanFactory) {
			((ConfigurableBeanFactory) this.beanFactory).registerDependentBean(unitName, requestingBeanName);
		}
		return emf;
	}
