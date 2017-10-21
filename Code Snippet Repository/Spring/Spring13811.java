	private RuntimeBeanReference registerUserDestResolver(Element brokerElem,
			RuntimeBeanReference userRegistry, ParserContext context, @Nullable Object source) {

		RootBeanDefinition beanDef = new RootBeanDefinition(DefaultUserDestinationResolver.class);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(0, userRegistry);
		if (brokerElem.hasAttribute("user-destination-prefix")) {
			beanDef.getPropertyValues().add("userDestinationPrefix", brokerElem.getAttribute("user-destination-prefix"));
		}
		if (brokerElem.hasAttribute("path-matcher")) {
			String pathMatcherRef = brokerElem.getAttribute("path-matcher");
			beanDef.getPropertyValues().add("pathMatcher", new RuntimeBeanReference(pathMatcherRef));
		}
		return new RuntimeBeanReference(registerBeanDef(beanDef, context, source));
	}
