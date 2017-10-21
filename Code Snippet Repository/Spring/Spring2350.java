	@Override
	protected void postProcess(BeanDefinitionBuilder definitionBuilder, Element element) {
		Object envValue = DomUtils.getChildElementValueByTagName(element, ENVIRONMENT);
		if (envValue != null) {
			// Specific environment settings defined, overriding any shared properties.
			definitionBuilder.addPropertyValue(JNDI_ENVIRONMENT, envValue);
		}
		else {
			// Check whether there is a reference to shared environment properties...
			String envRef = element.getAttribute(ENVIRONMENT_REF);
			if (StringUtils.hasLength(envRef)) {
				definitionBuilder.addPropertyValue(JNDI_ENVIRONMENT, new RuntimeBeanReference(envRef));
			}
		}

		String lazyInit = element.getAttribute(LAZY_INIT_ATTRIBUTE);
		if (StringUtils.hasText(lazyInit) && !DEFAULT_VALUE.equals(lazyInit)) {
			definitionBuilder.setLazyInit(TRUE_VALUE.equals(lazyInit));
		}
	}
