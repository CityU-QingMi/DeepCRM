	@Override
	public Object getObject() throws BeansException {
		if (isSingleton()) {
			return this.testBean;
		}
		else {
			TestBean prototype = new TestBean("prototype created at " + System.currentTimeMillis(), 11);
			if (this.beanFactory != null) {
				this.beanFactory.applyBeanPostProcessorsBeforeInitialization(prototype, this.beanName);
			}
			prototypeCreated = true;
			return prototype;
		}
	}
