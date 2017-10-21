	private void autodetect(Map<String, Object> beans, AutodetectCallback callback) {
		Assert.state(this.beanFactory != null, "No BeanFactory set");
		Set<String> beanNames = new LinkedHashSet<>(this.beanFactory.getBeanDefinitionCount());
		beanNames.addAll(Arrays.asList(this.beanFactory.getBeanDefinitionNames()));
		if (this.beanFactory instanceof ConfigurableBeanFactory) {
			beanNames.addAll(Arrays.asList(((ConfigurableBeanFactory) this.beanFactory).getSingletonNames()));
		}

		for (String beanName : beanNames) {
			if (!isExcluded(beanName) && !isBeanDefinitionAbstract(this.beanFactory, beanName)) {
				try {
					Class<?> beanClass = this.beanFactory.getType(beanName);
					if (beanClass != null && callback.include(beanClass, beanName)) {
						boolean lazyInit = isBeanDefinitionLazyInit(this.beanFactory, beanName);
						Object beanInstance = null;
						if (!lazyInit) {
							beanInstance = this.beanFactory.getBean(beanName);
							if (!beanClass.isInstance(beanInstance)) {
								continue;
							}
						}
						if (!ScopedProxyUtils.isScopedTarget(beanName) && !beans.containsValue(beanName) &&
								(beanInstance == null ||
										!CollectionUtils.containsInstance(beans.values(), beanInstance))) {
							// Not already registered for JMX exposure.
							beans.put(beanName, (beanInstance != null ? beanInstance : beanName));
							if (logger.isInfoEnabled()) {
								logger.info("Bean with name '" + beanName + "' has been autodetected for JMX exposure");
							}
						}
						else {
							if (logger.isDebugEnabled()) {
								logger.debug("Bean with name '" + beanName + "' is already registered for JMX exposure");
							}
						}
					}
				}
				catch (CannotLoadBeanClassException ex) {
					if (this.allowEagerInit) {
						throw ex;
					}
					// otherwise ignore beans where the class is not resolvable
				}
			}
		}
	}
