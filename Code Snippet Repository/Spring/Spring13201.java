	@Before
	public void setup() throws Exception {
		this.resolver = new UriComponentsBuilderMethodArgumentResolver();
		this.servletRequest = new MockHttpServletRequest();
		this.webRequest = new ServletWebRequest(this.servletRequest);

		Method method = this.getClass().getDeclaredMethod("handle", UriComponentsBuilder.class, ServletUriComponentsBuilder.class, int.class);
		this.builderParam = new MethodParameter(method, 0);
		this.servletBuilderParam = new MethodParameter(method, 1);
		this.intParam = new MethodParameter(method, 2);
	}
