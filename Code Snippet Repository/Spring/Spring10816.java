	@Before
	@SuppressWarnings("")
	public void setUp() throws Exception {
		GenericWebApplicationContext context = new GenericWebApplicationContext();
		context.refresh();
		resolver = new ExpressionValueMethodArgumentResolver(context.getBeanFactory());

		Method method = getClass().getMethod("params", int.class, String.class, String.class);
		paramSystemProperty = new MethodParameter(method, 0);
		paramContextPath = new MethodParameter(method, 1);
		paramNotSupported = new MethodParameter(method, 2);

		webRequest = new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse());

		// Expose request to the current thread (for SpEL expressions)
		RequestContextHolder.setRequestAttributes(webRequest);
	}
