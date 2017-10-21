	static private BeanDefinition createDatabasePopulator(Element element, List<Element> scripts, String execution) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CompositeDatabasePopulator.class);

		boolean ignoreFailedDrops = element.getAttribute("ignore-failures").equals("DROPS");
		boolean continueOnError = element.getAttribute("ignore-failures").equals("ALL");

		ManagedList<BeanMetadataElement> delegates = new ManagedList<>();
		for (Element scriptElement : scripts) {
			String executionAttr = scriptElement.getAttribute("execution");
			if (!StringUtils.hasText(executionAttr)) {
				executionAttr = "INIT";
			}
			if (!execution.equals(executionAttr)) {
				continue;
			}
			BeanDefinitionBuilder delegate = BeanDefinitionBuilder.genericBeanDefinition(ResourceDatabasePopulator.class);
			delegate.addPropertyValue("ignoreFailedDrops", ignoreFailedDrops);
			delegate.addPropertyValue("continueOnError", continueOnError);

			// Use a factory bean for the resources so they can be given an order if a pattern is used
			BeanDefinitionBuilder resourcesFactory = BeanDefinitionBuilder.genericBeanDefinition(SortedResourcesFactoryBean.class);
			resourcesFactory.addConstructorArgValue(new TypedStringValue(scriptElement.getAttribute("location")));
			delegate.addPropertyValue("scripts", resourcesFactory.getBeanDefinition());
			if (StringUtils.hasLength(scriptElement.getAttribute("encoding"))) {
				delegate.addPropertyValue("sqlScriptEncoding", new TypedStringValue(scriptElement.getAttribute("encoding")));
			}
			String separator = getSeparator(element, scriptElement);
			if (separator != null) {
				delegate.addPropertyValue("separator", new TypedStringValue(separator));
			}
			delegates.add(delegate.getBeanDefinition());
		}
		builder.addPropertyValue("populators", delegates);

		return builder.getBeanDefinition();
	}
