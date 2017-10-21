	@Before
	public void setup() throws Exception {
		this.handler = new StreamingResponseBodyReturnValueHandler();
		this.mavContainer = new ModelAndViewContainer();

		this.request = new MockHttpServletRequest("GET", "/path");
		this.response = new MockHttpServletResponse();
		this.webRequest = new ServletWebRequest(this.request, this.response);

		AsyncWebRequest asyncWebRequest = new StandardServletAsyncWebRequest(this.request, this.response);
		WebAsyncUtils.getAsyncManager(this.webRequest).setAsyncWebRequest(asyncWebRequest);
		this.request.setAsyncSupported(true);
	}
