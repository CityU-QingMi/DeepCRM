	protected boolean resolveProxyTargetClass(BeanDefinition beanDefinition) {
		boolean proxyTargetClass = this.defaultProxyTargetClass;
		Object attributeValue = beanDefinition.getAttribute(PROXY_TARGET_CLASS_ATTRIBUTE);
		if (attributeValue instanceof Boolean) {
			proxyTargetClass = (Boolean) attributeValue;
		}
		else if (attributeValue instanceof String) {
			proxyTargetClass = Boolean.valueOf((String) attributeValue);
		}
		else if (attributeValue != null) {
			throw new BeanDefinitionStoreException("Invalid proxy target class attribute [" +
					PROXY_TARGET_CLASS_ATTRIBUTE + "] with value '" + attributeValue +
					"': needs to be of type Boolean or String");
		}
		return proxyTargetClass;
	}
