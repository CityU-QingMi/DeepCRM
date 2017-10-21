	@Override
	public synchronized Class<?> getTargetClass() {
		if (this.targetClass == null && this.beanFactory != null) {
			// Determine type of the target bean.
			this.targetClass = this.beanFactory.getType(this.targetBeanName);
			if (this.targetClass == null) {
				if (logger.isTraceEnabled()) {
					logger.trace("Getting bean with name '" + this.targetBeanName + "' in order to determine type");
				}
				Object beanInstance = this.beanFactory.getBean(this.targetBeanName);
				this.targetClass = beanInstance.getClass();
			}
		}
		return this.targetClass;
	}
