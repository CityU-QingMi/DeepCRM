	@Before
	public void setUp() {
		ServletContext servletContext = new MockServletContext();
		MockHttpServletRequest mockRequest = new MockHttpServletRequest(servletContext);
		mockRequest.setAttribute(FROM_CUSTOM_MOCK, FROM_CUSTOM_MOCK);
		RequestContextHolder.setRequestAttributes(new ServletWebRequest(mockRequest, new MockHttpServletResponse()));

		this.wac.setServletContext(servletContext);
		new AnnotatedBeanDefinitionReader(this.wac).register(WebConfig.class);
		this.wac.refresh();

		this.mockMvc = webAppContextSetup(this.wac)
				.defaultRequest(get("/").requestAttr(FROM_MVC_TEST_DEFAULT, FROM_MVC_TEST_DEFAULT))
				.alwaysExpect(status().isOk())
				.build();
	}
