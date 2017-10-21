	@Before
	public void setup() {
		TestServletContext servletContext = new TestServletContext();
		servletContext.getMimeTypes().put("foo", "application/foo");

		this.servletRequest = new MockHttpServletRequest(servletContext);
		this.webRequest = new ServletWebRequest(this.servletRequest);

		this.factoryBean = new ContentNegotiationManagerFactoryBean();
		this.factoryBean.setServletContext(this.servletRequest.getServletContext());
	}
