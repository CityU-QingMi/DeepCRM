	private void addUrlBasedViewResolverProperties(Element element, RootBeanDefinition beanDefinition) {
		if (element.hasAttribute("prefix")) {
			beanDefinition.getPropertyValues().add("prefix", element.getAttribute("prefix"));
		}
		if (element.hasAttribute("suffix")) {
			beanDefinition.getPropertyValues().add("suffix", element.getAttribute("suffix"));
		}
		if (element.hasAttribute("cache-views")) {
			beanDefinition.getPropertyValues().add("cache", element.getAttribute("cache-views"));
		}
		if (element.hasAttribute("view-class")) {
			beanDefinition.getPropertyValues().add("viewClass", element.getAttribute("view-class"));
		}
		if (element.hasAttribute("view-names")) {
			beanDefinition.getPropertyValues().add("viewNames", element.getAttribute("view-names"));
		}
	}
