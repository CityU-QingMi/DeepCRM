	@Before
	public void setUp() throws Exception {
		MockServletContext servletContext = new MockServletContext();
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(servletContext);
		wac.refresh();

		request = new MockHttpServletRequest();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		response = new MockHttpServletResponse();

		renderer = mock(Renderer.class);

		view = new TilesView();
		view.setServletContext(servletContext);
		view.setRenderer(renderer);
		view.setUrl(VIEW_PATH);
		view.afterPropertiesSet();
	}
