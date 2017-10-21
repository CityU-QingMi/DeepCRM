	protected DefaultListableBeanFactory buildInternalBeanFactory(ConfigurableBeanFactory containingFactory) {
		// Set parent so that references (up container hierarchies) are correctly resolved.
		DefaultListableBeanFactory internalBeanFactory = new DefaultListableBeanFactory(containingFactory);

		// Required so that all BeanPostProcessors, Scopes, etc become available.
		internalBeanFactory.copyConfigurationFrom(containingFactory);

		// Filter out BeanPostProcessors that are part of the AOP infrastructure,
		// since those are only meant to apply to beans defined in the original factory.
		for (Iterator<BeanPostProcessor> it = internalBeanFactory.getBeanPostProcessors().iterator(); it.hasNext();) {
			if (it.next() instanceof AopInfrastructureBean) {
				it.remove();
			}
		}

		return internalBeanFactory;
	}
