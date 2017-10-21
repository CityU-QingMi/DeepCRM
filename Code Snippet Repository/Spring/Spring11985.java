	private void parseResourceChain(RootBeanDefinition resourceHandlerDef, ParserContext parserContext,
			Element element, @Nullable Object source) {

		String autoRegistration = element.getAttribute("auto-registration");
		boolean isAutoRegistration = !(StringUtils.hasText(autoRegistration) && "false".equals(autoRegistration));

		ManagedList<? super Object> resourceResolvers = new ManagedList<>();
		resourceResolvers.setSource(source);
		ManagedList<? super Object> resourceTransformers = new ManagedList<>();
		resourceTransformers.setSource(source);

		parseResourceCache(resourceResolvers, resourceTransformers, element, source);
		parseResourceResolversTransformers(isAutoRegistration, resourceResolvers, resourceTransformers,
				parserContext, element, source);

		if (!resourceResolvers.isEmpty()) {
			resourceHandlerDef.getPropertyValues().add("resourceResolvers", resourceResolvers);
		}
		if (!resourceTransformers.isEmpty()) {
			resourceHandlerDef.getPropertyValues().add("resourceTransformers", resourceTransformers);
		}
	}
