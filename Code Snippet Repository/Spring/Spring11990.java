	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		List<Element> childElements = DomUtils.getChildElementsByTagName(element, "script");
		if (!childElements.isEmpty()) {
			List<String> locations = new ArrayList<>(childElements.size());
			for (Element childElement : childElements) {
				locations.add(childElement.getAttribute("location"));
			}
			builder.addPropertyValue("scripts", locations.toArray(new String[locations.size()]));
		}
		builder.addPropertyValue("engineName", element.getAttribute("engine-name"));
		if (element.hasAttribute("render-object")) {
			builder.addPropertyValue("renderObject", element.getAttribute("render-object"));
		}
		if (element.hasAttribute("render-function")) {
			builder.addPropertyValue("renderFunction", element.getAttribute("render-function"));
		}
		if (element.hasAttribute("content-type")) {
			builder.addPropertyValue("contentType", element.getAttribute("content-type"));
		}
		if (element.hasAttribute("charset")) {
			builder.addPropertyValue("charset", Charset.forName(element.getAttribute("charset")));
		}
		if (element.hasAttribute("resource-loader-path")) {
			builder.addPropertyValue("resourceLoaderPath", element.getAttribute("resource-loader-path"));
		}
		if (element.hasAttribute("shared-engine")) {
			builder.addPropertyValue("sharedEngine", element.getAttribute("shared-engine"));
		}
	}
