	@Before
	public void setup() throws Exception {

		ReactiveAdapterRegistry adapterRegistry = new ReactiveAdapterRegistry();
		this.resolver = new RequestParamMethodArgumentResolver(null, adapterRegistry, true);

		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultFormattingConversionService());
		this.bindContext = new BindingContext(initializer);
	}
