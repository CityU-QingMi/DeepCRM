	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		super.doParse(element, parserContext, builder);

		String defaultValue = element.getAttribute(DEFAULT_VALUE);
		String defaultRef = element.getAttribute(DEFAULT_REF);
		if (StringUtils.hasLength(defaultValue)) {
			if (StringUtils.hasLength(defaultRef)) {
				parserContext.getReaderContext().error("<jndi-lookup> element is only allowed to contain either " +
						"'default-value' attribute OR 'default-ref' attribute, not both", element);
			}
			builder.addPropertyValue(DEFAULT_OBJECT, defaultValue);
		}
		else if (StringUtils.hasLength(defaultRef)) {
			builder.addPropertyValue(DEFAULT_OBJECT, new RuntimeBeanReference(defaultRef));
		}
	}
