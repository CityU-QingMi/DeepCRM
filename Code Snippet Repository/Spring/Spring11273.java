	private void addResolversTo(ArgumentResolverRegistrar registrar,
			ReactiveAdapterRegistry reactiveRegistry, ConfigurableApplicationContext context) {

		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

		// Annotation-based...
		registrar.add(new RequestParamMethodArgumentResolver(beanFactory, reactiveRegistry, false));
		registrar.add(new RequestParamMapMethodArgumentResolver(reactiveRegistry));
		registrar.add(new PathVariableMethodArgumentResolver(beanFactory, reactiveRegistry));
		registrar.add(new PathVariableMapMethodArgumentResolver(reactiveRegistry));
		registrar.addIfRequestBody(readers -> new RequestBodyArgumentResolver(readers, reactiveRegistry));
		registrar.addIfRequestBody(readers -> new RequestPartMethodArgumentResolver(readers, reactiveRegistry));
		registrar.addIfModelAttribute(() -> new ModelAttributeMethodArgumentResolver(reactiveRegistry, false));
		registrar.add(new RequestHeaderMethodArgumentResolver(beanFactory, reactiveRegistry));
		registrar.add(new RequestHeaderMapMethodArgumentResolver(reactiveRegistry));
		registrar.add(new CookieValueMethodArgumentResolver(beanFactory, reactiveRegistry));
		registrar.add(new ExpressionValueMethodArgumentResolver(beanFactory, reactiveRegistry));
		registrar.add(new SessionAttributeMethodArgumentResolver(beanFactory, reactiveRegistry));
		registrar.add(new RequestAttributeMethodArgumentResolver(beanFactory, reactiveRegistry));

		// Type-based...
		registrar.addIfRequestBody(readers -> new HttpEntityArgumentResolver(readers, reactiveRegistry));
		registrar.add(new ModelArgumentResolver(reactiveRegistry));
		registrar.addIfModelAttribute(() -> new ErrorsMethodArgumentResolver(reactiveRegistry));
		registrar.add(new ServerWebExchangeArgumentResolver(reactiveRegistry));
		registrar.add(new PrincipalArgumentResolver(reactiveRegistry));
		registrar.addIfRequestBody(readers -> new SessionStatusMethodArgumentResolver());
		registrar.add(new WebSessionArgumentResolver(reactiveRegistry));

		// Custom...
		registrar.addCustomResolvers();

		// Catch-all...
		registrar.add(new RequestParamMethodArgumentResolver(beanFactory, reactiveRegistry, true));
		registrar.addIfModelAttribute(() -> new ModelAttributeMethodArgumentResolver(reactiveRegistry, true));
	}
