	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		List<Element> childElements = DomUtils.getChildElementsByTagName(element, "template-loader-path");
		if (!childElements.isEmpty()) {
			List<String> locations = new ArrayList<>(childElements.size());
			for (Element childElement : childElements) {
				locations.add(childElement.getAttribute("location"));
			}
			if (locations.isEmpty()) {
				locations.add("/WEB-INF/");
			}
			builder.addPropertyValue("templateLoaderPaths", locations.toArray(new String[locations.size()]));
		}
	}
