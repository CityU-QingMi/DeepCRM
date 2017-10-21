	protected DefaultListableBeanFactory getInternalBeanFactoryForBean(String beanName) {
		synchronized (this.internalBeanFactories) {
			DefaultListableBeanFactory internalBeanFactory = this.internalBeanFactories.get(beanName);
			if (internalBeanFactory == null) {
				internalBeanFactory = buildInternalBeanFactory(this.beanFactory);
				this.internalBeanFactories.put(beanName, internalBeanFactory);
			}
			return internalBeanFactory;
		}
	}
