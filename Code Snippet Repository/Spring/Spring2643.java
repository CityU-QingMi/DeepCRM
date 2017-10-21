	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		if (!(beanFactory instanceof ConfigurableBeanFactory)) {
			throw new IllegalStateException("ScriptFactoryPostProcessor doesn't work with " +
					"non-ConfigurableBeanFactory: " + beanFactory.getClass());
		}
		this.beanFactory = (ConfigurableBeanFactory) beanFactory;

		// Required so that references (up container hierarchies) are correctly resolved.
		this.scriptBeanFactory.setParentBeanFactory(this.beanFactory);

		// Required so that all BeanPostProcessors, Scopes, etc become available.
		this.scriptBeanFactory.copyConfigurationFrom(this.beanFactory);

		// Filter out BeanPostProcessors that are part of the AOP infrastructure,
		// since those are only meant to apply to beans defined in the original factory.
		for (Iterator<BeanPostProcessor> it = this.scriptBeanFactory.getBeanPostProcessors().iterator(); it.hasNext();) {
			if (it.next() instanceof AopInfrastructureBean) {
				it.remove();
			}
		}
	}
