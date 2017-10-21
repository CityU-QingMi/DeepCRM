	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		List<Element> childElements = DomUtils.getChildElementsByTagName(element, "definitions");
		if (!childElements.isEmpty()) {
			List<String> locations = new ArrayList<>(childElements.size());
			for (Element childElement : childElements) {
				locations.add(childElement.getAttribute("location"));
			}
			builder.addPropertyValue("definitions", locations.toArray(new String[locations.size()]));
		}
		if (element.hasAttribute("check-refresh")) {
			builder.addPropertyValue("checkRefresh", element.getAttribute("check-refresh"));
		}
		if (element.hasAttribute("validate-definitions")) {
			builder.addPropertyValue("validateDefinitions", element.getAttribute("validate-definitions"));
		}
		if (element.hasAttribute("definitions-factory")) {
			builder.addPropertyValue("definitionsFactoryClass", element.getAttribute("definitions-factory"));
		}
		if (element.hasAttribute("preparer-factory")) {
			builder.addPropertyValue("preparerFactoryClass", element.getAttribute("preparer-factory"));
		}
	}
