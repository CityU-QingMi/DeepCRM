	@Before
	public void setUp() throws Exception {
		resolver = new TestCookieValueMethodArgumentResolver();

		Method method = getClass().getMethod("params", Cookie.class, String.class, String.class);
		paramNamedCookie = new SynthesizingMethodParameter(method, 0);
		paramNamedDefaultValueString = new SynthesizingMethodParameter(method, 1);
		paramString = new SynthesizingMethodParameter(method, 2);

		request = new MockHttpServletRequest();
		webRequest = new ServletWebRequest(request, new MockHttpServletResponse());
	}
