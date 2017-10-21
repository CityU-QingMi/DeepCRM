	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (isLogEnabled()) {
			String[] beanNames = beanFactory.getBeanDefinitionNames();
			for (String beanName : beanNames) {
				String nameToLookup = beanName;
				if (beanFactory.isFactoryBean(beanName)) {
					nameToLookup = BeanFactory.FACTORY_BEAN_PREFIX + beanName;
				}
				Class<?> beanType = beanFactory.getType(nameToLookup);
				if (beanType != null) {
					Class<?> userClass = ClassUtils.getUserClass(beanType);
					if (userClass.isAnnotationPresent(Deprecated.class)) {
						BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
						logDeprecatedBean(beanName, beanType, beanDefinition);
					}
				}
			}
		}
	}
