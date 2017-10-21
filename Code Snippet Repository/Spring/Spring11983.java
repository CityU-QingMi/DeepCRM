	private void registerUrlProvider(ParserContext parserContext, @Nullable Object source) {
		if (!parserContext.getRegistry().containsBeanDefinition(RESOURCE_URL_PROVIDER)) {
			RootBeanDefinition urlProvider = new RootBeanDefinition(ResourceUrlProvider.class);
			urlProvider.setSource(source);
			urlProvider.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			parserContext.getRegistry().registerBeanDefinition(RESOURCE_URL_PROVIDER, urlProvider);
			parserContext.registerComponent(new BeanComponentDefinition(urlProvider, RESOURCE_URL_PROVIDER));

			RootBeanDefinition interceptor = new RootBeanDefinition(ResourceUrlProviderExposingInterceptor.class);
			interceptor.setSource(source);
			interceptor.getConstructorArgumentValues().addIndexedArgumentValue(0, urlProvider);

			RootBeanDefinition mappedInterceptor = new RootBeanDefinition(MappedInterceptor.class);
			mappedInterceptor.setSource(source);
			mappedInterceptor.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			mappedInterceptor.getConstructorArgumentValues().addIndexedArgumentValue(0, (Object) null);
			mappedInterceptor.getConstructorArgumentValues().addIndexedArgumentValue(1, interceptor);
			String mappedInterceptorName = parserContext.getReaderContext().registerWithGeneratedName(mappedInterceptor);
			parserContext.registerComponent(new BeanComponentDefinition(mappedInterceptor, mappedInterceptorName));
		}
	}
