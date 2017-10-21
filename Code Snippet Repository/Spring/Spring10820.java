	@Before
	public void setUp() throws Exception {
		processor = new MapMethodProcessor();
		mavContainer = new ModelAndViewContainer();

		Method method = getClass().getDeclaredMethod("map", Map.class);
		paramMap = new MethodParameter(method, 0);
		returnParamMap = new MethodParameter(method, 0);

		webRequest = new ServletWebRequest(new MockHttpServletRequest());
	}
