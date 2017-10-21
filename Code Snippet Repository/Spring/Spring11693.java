	@Before
	public void setup() throws Exception {
		AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext();
		context1.register(WebApp1Config.class);
		context1.refresh();

		AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext();
		context2.register(WebApp2Config.class);
		context2.refresh();

		HttpHandler webApp1Handler = WebHttpHandlerBuilder.applicationContext(context1).build();
		HttpHandler webApp2Handler = WebHttpHandlerBuilder.applicationContext(context2).build();

		this.server = new ReactorHttpServer();

		this.server.registerHttpHandler("/webApp1", webApp1Handler);
		this.server.registerHttpHandler("/webApp2", webApp2Handler);

		this.server.afterPropertiesSet();
		this.server.start();
	}
