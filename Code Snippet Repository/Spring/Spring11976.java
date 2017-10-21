	public static RuntimeBeanReference registerUrlPathHelper(
			@Nullable RuntimeBeanReference urlPathHelperRef, ParserContext parserContext, @Nullable Object source) {

		if (urlPathHelperRef != null) {
			if (parserContext.getRegistry().isAlias(URL_PATH_HELPER_BEAN_NAME)) {
				parserContext.getRegistry().removeAlias(URL_PATH_HELPER_BEAN_NAME);
			}
			parserContext.getRegistry().registerAlias(urlPathHelperRef.getBeanName(), URL_PATH_HELPER_BEAN_NAME);
		}
		else if (!parserContext.getRegistry().isAlias(URL_PATH_HELPER_BEAN_NAME)
				&& !parserContext.getRegistry().containsBeanDefinition(URL_PATH_HELPER_BEAN_NAME)) {
			RootBeanDefinition urlPathHelperDef = new RootBeanDefinition(UrlPathHelper.class);
			urlPathHelperDef.setSource(source);
			urlPathHelperDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			parserContext.getRegistry().registerBeanDefinition(URL_PATH_HELPER_BEAN_NAME, urlPathHelperDef);
			parserContext.registerComponent(new BeanComponentDefinition(urlPathHelperDef, URL_PATH_HELPER_BEAN_NAME));
		}
		return new RuntimeBeanReference(URL_PATH_HELPER_BEAN_NAME);
	}
