	@Before
	public void setup() throws Exception {
		Method method = getClass().getDeclaredMethod("handle", HttpEntity.class, HttpEntity.class);
		paramList = new MethodParameter(method, 0);
		paramSimpleBean = new MethodParameter(method, 1);

		mavContainer = new ModelAndViewContainer();
		binderFactory = new ValidatingBinderFactory();
		servletRequest = new MockHttpServletRequest();
		servletResponse = new MockHttpServletResponse();
		servletRequest.setMethod("POST");
		webRequest = new ServletWebRequest(servletRequest, servletResponse);
	}
