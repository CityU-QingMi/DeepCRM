	@Before
	public void setup() throws Exception {
		container = new ModelAndViewContainer();
		servletRequest = new MockHttpServletRequest();
		servletRequest.setMethod("POST");
		servletResponse = new MockHttpServletResponse();
		request = new ServletWebRequest(servletRequest, servletResponse);
		this.factory = new ValidatingBinderFactory();

		Method method = getClass().getDeclaredMethod("handle",
				List.class, SimpleBean.class, MultiValueMap.class, String.class);
		paramGenericList = new MethodParameter(method, 0);
		paramSimpleBean = new MethodParameter(method, 1);
		paramMultiValueMap = new MethodParameter(method, 2);
		paramString = new MethodParameter(method, 3);
		returnTypeString = new MethodParameter(method, -1);
	}
