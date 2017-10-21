	@Before
	public void setup() {
		this.body = "body";
		this.contentType = MediaType.TEXT_PLAIN;
		this.converterType = StringHttpMessageConverter.class;
		this.paramType = new MethodParameter(ClassUtils.getMethod(this.getClass(), "handle", String.class), 0);
		this.returnType = new MethodParameter(ClassUtils.getMethod(this.getClass(), "handle", String.class), -1);
		this.request = new ServletServerHttpRequest(new MockHttpServletRequest());
		this.response = new ServletServerHttpResponse(new MockHttpServletResponse());
	}
