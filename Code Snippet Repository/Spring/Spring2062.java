	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		builder.addPropertyReference("cacheManager", CacheNamespaceHandler.extractCacheManager(element));
		CacheNamespaceHandler.parseKeyGenerator(element, builder.getBeanDefinition());

		List<Element> cacheDefs = DomUtils.getChildElementsByTagName(element, DEFS_ELEMENT);
		if (cacheDefs.size() >= 1) {
			// Using attributes source.
			List<RootBeanDefinition> attributeSourceDefinitions = parseDefinitionsSources(cacheDefs, parserContext);
			builder.addPropertyValue("cacheOperationSources", attributeSourceDefinitions);
		}
		else {
			// Assume annotations source.
			builder.addPropertyValue("cacheOperationSources",
					new RootBeanDefinition("org.springframework.cache.annotation.AnnotationCacheOperationSource"));
		}
	}
