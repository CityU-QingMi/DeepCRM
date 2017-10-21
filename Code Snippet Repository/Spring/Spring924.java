		@Override
		protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
			Properties parsedProps = parserContext.getDelegate().parsePropsElement(element);
			builder.addPropertyValue("properties", parsedProps);

			String location = element.getAttribute("location");
			if (StringUtils.hasLength(location)) {
				location = parserContext.getReaderContext().getEnvironment().resolvePlaceholders(location);
				String[] locations = StringUtils.commaDelimitedListToStringArray(location);
				builder.addPropertyValue("locations", locations);
			}

			builder.addPropertyValue("ignoreResourceNotFound",
					Boolean.valueOf(element.getAttribute("ignore-resource-not-found")));

			builder.addPropertyValue("localOverride",
					Boolean.valueOf(element.getAttribute("local-override")));

			String scope = element.getAttribute(SCOPE_ATTRIBUTE);
			if (StringUtils.hasLength(scope)) {
				builder.setScope(scope);
			}
		}
