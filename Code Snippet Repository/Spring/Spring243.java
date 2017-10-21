	protected void destroyPrototypeInstance(Object target) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Destroying instance of bean '" + getTargetBeanName() + "'");
		}
		if (getBeanFactory() instanceof ConfigurableBeanFactory) {
			((ConfigurableBeanFactory) getBeanFactory()).destroyBean(getTargetBeanName(), target);
		}
		else if (target instanceof DisposableBean) {
			try {
				((DisposableBean) target).destroy();
			}
			catch (Throwable ex) {
				logger.error("Couldn't invoke destroy method of bean with name '" + getTargetBeanName() + "'", ex);
			}
		}
	}
