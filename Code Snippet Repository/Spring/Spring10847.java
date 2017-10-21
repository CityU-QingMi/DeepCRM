	@Before
	@SuppressWarnings("")
	public void setUp() throws Exception {
		GenericWebApplicationContext context = new GenericWebApplicationContext();
		context.refresh();
		resolver = new RequestHeaderMethodArgumentResolver(context.getBeanFactory());

		Method method = ReflectionUtils.findMethod(getClass(), "params", (Class<?>[]) null);
		paramNamedDefaultValueStringHeader = new SynthesizingMethodParameter(method, 0);
		paramNamedValueStringArray = new SynthesizingMethodParameter(method, 1);
		paramSystemProperty = new SynthesizingMethodParameter(method, 2);
		paramContextPath = new SynthesizingMethodParameter(method, 3);
		paramResolvedNameWithExpression = new SynthesizingMethodParameter(method, 4);
		paramResolvedNameWithPlaceholder = new SynthesizingMethodParameter(method, 5);
		paramNamedValueMap = new SynthesizingMethodParameter(method, 6);
		paramDate = new SynthesizingMethodParameter(method, 7);
		paramInstant = new SynthesizingMethodParameter(method, 8);

		servletRequest = new MockHttpServletRequest();
		webRequest = new ServletWebRequest(servletRequest, new MockHttpServletResponse());

		// Expose request to the current thread (for SpEL expressions)
		RequestContextHolder.setRequestAttributes(webRequest);
	}
