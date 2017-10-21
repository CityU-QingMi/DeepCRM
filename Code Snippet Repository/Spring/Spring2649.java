	protected long resolveRefreshCheckDelay(BeanDefinition beanDefinition) {
		long refreshCheckDelay = this.defaultRefreshCheckDelay;
		Object attributeValue = beanDefinition.getAttribute(REFRESH_CHECK_DELAY_ATTRIBUTE);
		if (attributeValue instanceof Number) {
			refreshCheckDelay = ((Number) attributeValue).longValue();
		}
		else if (attributeValue instanceof String) {
			refreshCheckDelay = Long.parseLong((String) attributeValue);
		}
		else if (attributeValue != null) {
			throw new BeanDefinitionStoreException("Invalid refresh check delay attribute [" +
					REFRESH_CHECK_DELAY_ATTRIBUTE + "] with value '" + attributeValue +
					"': needs to be of type Number or String");
		}
		return refreshCheckDelay;
	}
