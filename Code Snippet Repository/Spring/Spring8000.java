	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder beanDefinitionBuilder) {
		String contextPath = element.getAttribute("context-path");
		if (StringUtils.hasText(contextPath)) {
			beanDefinitionBuilder.addPropertyValue("contextPath", contextPath);
		}

		List<Element> classes = DomUtils.getChildElementsByTagName(element, "class-to-be-bound");
		if (!classes.isEmpty()) {
			ManagedList<String> classesToBeBound = new ManagedList<>(classes.size());
			for (Element classToBeBound : classes) {
				String className = classToBeBound.getAttribute("name");
				classesToBeBound.add(className);
			}
			beanDefinitionBuilder.addPropertyValue("classesToBeBound", classesToBeBound);
		}
	}
