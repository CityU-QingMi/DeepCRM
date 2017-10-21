	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.applicationContext, "ApplicationContext is required");

		if (CollectionUtils.isEmpty(this.messageReaders)) {
			ServerCodecConfigurer codecConfigurer = ServerCodecConfigurer.create();
			this.messageReaders = codecConfigurer.getReaders();
		}
		if (this.argumentResolverConfigurer == null) {
			this.argumentResolverConfigurer = new ArgumentResolverConfigurer();
		}
		if (this.reactiveAdapterRegistry == null) {
			this.reactiveAdapterRegistry = new ReactiveAdapterRegistry();
		}

		this.methodResolver = new ControllerMethodResolver(this.argumentResolverConfigurer,
				this.messageReaders, this.reactiveAdapterRegistry, this.applicationContext);

		this.modelInitializer = new ModelInitializer(this.methodResolver, this.reactiveAdapterRegistry);
	}
