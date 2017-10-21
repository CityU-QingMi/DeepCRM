	@Before
	public void setup() {
		StaticWebApplicationContext context = new StaticWebApplicationContext();
		context.registerSingleton("freeMarkerConfigurer", FreeMarkerConfigurer.class);
		context.registerSingleton("tilesConfigurer", TilesConfigurer.class);
		context.registerSingleton("groovyMarkupConfigurer", GroovyMarkupConfigurer.class);
		context.registerSingleton("scriptTemplateConfigurer", ScriptTemplateConfigurer.class);

		this.registry = new ViewResolverRegistry(new ContentNegotiationManager(), context);
	}
