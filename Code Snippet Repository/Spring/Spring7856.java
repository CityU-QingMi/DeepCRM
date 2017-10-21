	protected EntityManagerFactory findDefaultEntityManagerFactory(@Nullable String requestingBeanName)
			throws NoSuchBeanDefinitionException {

		Assert.state(this.beanFactory != null, "ListableBeanFactory required for EntityManagerFactory bean lookup");

		if (this.beanFactory instanceof ConfigurableListableBeanFactory) {
			// Fancy variant with dependency registration
			ConfigurableListableBeanFactory clbf = (ConfigurableListableBeanFactory) this.beanFactory;
			NamedBeanHolder<EntityManagerFactory> emfHolder = clbf.resolveNamedBean(EntityManagerFactory.class);
			if (requestingBeanName != null) {
				clbf.registerDependentBean(emfHolder.getBeanName(), requestingBeanName);
			}
			return emfHolder.getBeanInstance();
		}
		else {
			// Plain variant: just find a default bean
			return this.beanFactory.getBean(EntityManagerFactory.class);
		}
	}
