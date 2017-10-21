	@Before
	public void setUp() throws Exception {

		ReactiveAdapterRegistry adapterRegistry = new ReactiveAdapterRegistry();

		ArgumentResolverConfigurer resolverConfigurer = new ArgumentResolverConfigurer();
		resolverConfigurer.addCustomResolver(new ModelArgumentResolver(adapterRegistry));

		ControllerMethodResolver methodResolver = new ControllerMethodResolver(
				resolverConfigurer, Collections.emptyList(), adapterRegistry, new StaticApplicationContext());

		this.modelInitializer = new ModelInitializer(methodResolver, adapterRegistry);
	}
