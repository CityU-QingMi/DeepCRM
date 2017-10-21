	@Before
	public void setup() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();
		ReactiveAdapterRegistry adapterRegistry = new ReactiveAdapterRegistry();
		this.resolver = new SessionAttributeMethodArgumentResolver(context.getBeanFactory(), adapterRegistry);

		this.session = mock(WebSession.class);
		WebSessionManager sessionManager = new MockWebSessionManager(this.session);

		ServerHttpRequest request = MockServerHttpRequest.get("/").build();
		this.exchange = new DefaultServerWebExchange(request, new MockServerHttpResponse(),
				sessionManager, ServerCodecConfigurer.create(), new AcceptHeaderLocaleContextResolver());

		this.handleMethod = ReflectionUtils.findMethod(getClass(), "handleWithSessionAttribute", (Class<?>[]) null);
	}
