	@Before
	public void setUp() throws Exception {
		processor = new ModelMethodProcessor();
		mavContainer = new ModelAndViewContainer();

		Method method = getClass().getDeclaredMethod("model", Model.class);
		paramModel = new MethodParameter(method, 0);
		returnParamModel = new MethodParameter(method, -1);

		webRequest = new ServletWebRequest(new MockHttpServletRequest());
	}
