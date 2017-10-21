	@Before
	public void setUp() throws Exception {
		TestMockServletContext servletContext = new TestMockServletContext();
		appContext = new GenericWebApplicationContext();
		appContext.setServletContext(servletContext);
		LocaleContextHolder.setLocale(Locale.US);

		String attributeName = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
		appContext.getServletContext().setAttribute(attributeName, appContext);

		handler = new TestController();
		Method method = TestController.class.getMethod("testBind", Date.class, Double.class, TestBean.class, BindingResult.class);
		handlerMethod = new InvocableHandlerMethod(handler, method);
	}
