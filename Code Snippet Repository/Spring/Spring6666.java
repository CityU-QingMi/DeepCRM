	@Override
	protected MutablePropertyValues parseCommonContainerProperties(Element containerEle, ParserContext parserContext) {
		MutablePropertyValues properties = super.parseCommonContainerProperties(containerEle, parserContext);

		Integer acknowledgeMode = parseAcknowledgeMode(containerEle, parserContext);
		if (acknowledgeMode != null) {
			properties.add("acknowledgeMode", acknowledgeMode);
		}

		String concurrency = containerEle.getAttribute(CONCURRENCY_ATTRIBUTE);
		if (StringUtils.hasText(concurrency)) {
			properties.add("concurrency", concurrency);
		}

		String prefetch = containerEle.getAttribute(PREFETCH_ATTRIBUTE);
		if (StringUtils.hasText(prefetch)) {
			properties.add("prefetchSize", Integer.valueOf(prefetch));
		}

		return properties;
	}
