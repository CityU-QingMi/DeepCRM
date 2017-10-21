	@Before
	public void setUp() throws Exception {
		adaptee = mock(WebArgumentResolver.class);
		adapter = new TestWebArgumentResolverAdapter(adaptee);
		parameter = new MethodParameter(getClass().getMethod("handle", Integer.TYPE), 0);
		webRequest = new ServletWebRequest(new MockHttpServletRequest());

		// Expose request to the current thread (for SpEL expressions)
		RequestContextHolder.setRequestAttributes(webRequest);
	}
