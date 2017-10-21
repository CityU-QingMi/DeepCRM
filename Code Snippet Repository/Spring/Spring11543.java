	@Before
	public void createContext() {
		HandlerStrategies strategies = HandlerStrategies.withDefaults();
		context = new ServerResponse.Context() {
			@Override
			public List<HttpMessageWriter<?>> messageWriters() {
				return strategies.messageWriters();
			}

			@Override
			public List<ViewResolver> viewResolvers() {
				return strategies.viewResolvers();
			}
		};

	}
