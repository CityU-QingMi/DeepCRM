	private BeanDefinition createContentNegotiatingViewResolver(Element resolverElement, ParserContext context) {
		RootBeanDefinition beanDef = new RootBeanDefinition(ContentNegotiatingViewResolver.class);
		beanDef.setSource(context.extractSource(resolverElement));
		beanDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		MutablePropertyValues values = beanDef.getPropertyValues();

		List<Element> elements = DomUtils.getChildElementsByTagName(resolverElement, new String[] {"default-views"});
		if (!elements.isEmpty()) {
			ManagedList<Object> list = new ManagedList<>();
			for (Element element : DomUtils.getChildElementsByTagName(elements.get(0), "bean", "ref")) {
				list.add(context.getDelegate().parsePropertySubElement(element, null));
			}
			values.add("defaultViews", list);
		}
		if (resolverElement.hasAttribute("use-not-acceptable")) {
			values.add("useNotAcceptableStatusCode", resolverElement.getAttribute("use-not-acceptable"));
		}
		Object manager = MvcNamespaceUtils.getContentNegotiationManager(context);
		if (manager != null) {
			values.add("contentNegotiationManager", manager);
		}
		return beanDef;
	}
