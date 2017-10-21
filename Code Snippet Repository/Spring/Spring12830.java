	@Before
	public void setup() throws Exception {
		HttpServletRequest request = new MockHttpServletRequest();
		HttpServletResponse response = new MockHttpServletResponse();
		this.webRequest = new ServletWebRequest(request, response);

		this.resolver = createResolver();

		this.handleMethod = AbstractRequestAttributesArgumentResolverTests.class
				.getDeclaredMethod(getHandleMethodName(), Foo.class, Foo.class, Foo.class, Optional.class);
	}
