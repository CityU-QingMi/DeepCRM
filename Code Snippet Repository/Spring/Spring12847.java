	@Before
	public void setup() throws Exception {
		this.handler = new DeferredResultMethodReturnValueHandler();
		this.request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		this.webRequest = new ServletWebRequest(this.request, response);

		AsyncWebRequest asyncWebRequest = new StandardServletAsyncWebRequest(this.request, response);
		WebAsyncUtils.getAsyncManager(this.webRequest).setAsyncWebRequest(asyncWebRequest);
		this.request.setAsyncSupported(true);
	}
