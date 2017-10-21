	public static RuntimeBeanReference registerPathMatcher(@Nullable RuntimeBeanReference pathMatcherRef,
			ParserContext parserContext, @Nullable Object source) {

		if (pathMatcherRef != null) {
			if (parserContext.getRegistry().isAlias(PATH_MATCHER_BEAN_NAME)) {
				parserContext.getRegistry().removeAlias(PATH_MATCHER_BEAN_NAME);
			}
			parserContext.getRegistry().registerAlias(pathMatcherRef.getBeanName(), PATH_MATCHER_BEAN_NAME);
		}
		else if (!parserContext.getRegistry().isAlias(PATH_MATCHER_BEAN_NAME)
				&& !parserContext.getRegistry().containsBeanDefinition(PATH_MATCHER_BEAN_NAME)) {
			RootBeanDefinition pathMatcherDef = new RootBeanDefinition(AntPathMatcher.class);
			pathMatcherDef.setSource(source);
			pathMatcherDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			parserContext.getRegistry().registerBeanDefinition(PATH_MATCHER_BEAN_NAME, pathMatcherDef);
			parserContext.registerComponent(new BeanComponentDefinition(pathMatcherDef, PATH_MATCHER_BEAN_NAME));
		}
		return new RuntimeBeanReference(PATH_MATCHER_BEAN_NAME);
	}
