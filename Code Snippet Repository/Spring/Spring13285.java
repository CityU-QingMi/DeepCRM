	@Before
	@SuppressWarnings("")
	public void setup() throws Exception {
		this.filterChain = new MockFilterChain(this.servlet, new ResourceUrlEncodingFilter());

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setServletContext(new MockServletContext());
		context.register(WebConfig.class);
		context.refresh();

		this.request = new MockHttpServletRequest("GET", "/");
		this.request.setContextPath("/myapp");
		this.response = new MockHttpServletResponse();

		Object urlProvider = context.getBean(ResourceUrlProvider.class);
		this.request.setAttribute(ResourceUrlProviderExposingInterceptor.RESOURCE_URL_PROVIDER_ATTR, urlProvider);
	}
