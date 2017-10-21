	@Before
	public void setup() throws Exception {
		ConfigurableWebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();
		bindingInitializer.setValidator(new StubValidator());

		List<HandlerMethodArgumentResolver> customResolvers = new ArrayList<>();
		customResolvers.add(new ServletWebArgumentResolverAdapter(new ColorArgumentResolver()));

		GenericWebApplicationContext context = new GenericWebApplicationContext();
		context.refresh();

		handlerAdapter = new RequestMappingHandlerAdapter();
		handlerAdapter.setWebBindingInitializer(bindingInitializer);
		handlerAdapter.setCustomArgumentResolvers(customResolvers);
		handlerAdapter.setApplicationContext(context);
		handlerAdapter.setBeanFactory(context.getBeanFactory());
		handlerAdapter.afterPropertiesSet();

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();

		request.setMethod("POST");

		// Expose request to the current thread (for SpEL expressions)
		RequestContextHolder.setRequestAttributes(new ServletWebRequest(request));
	}
