	@Before
	public void setUp() throws Exception {

		ArgumentResolverConfigurer resolvers = new ArgumentResolverConfigurer();
		resolvers.addCustomResolver(new CustomArgumentResolver());
		resolvers.addCustomResolver(new CustomSyncArgumentResolver());

		ServerCodecConfigurer codecs = ServerCodecConfigurer.create();
		codecs.customCodecs().decoder(new ByteArrayDecoder());
		codecs.customCodecs().decoder(new ByteBufferDecoder());

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.registerBean(TestControllerAdvice.class);
		applicationContext.refresh();

		this.methodResolver = new ControllerMethodResolver(
				resolvers, codecs.getReaders(), new ReactiveAdapterRegistry(), applicationContext);

		Method method = ResolvableMethod.on(TestController.class).mockCall(TestController::handle).method();
		this.handlerMethod = new HandlerMethod(new TestController(), method);
	}
