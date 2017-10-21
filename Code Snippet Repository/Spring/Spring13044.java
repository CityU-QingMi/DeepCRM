	@Before
	public void setup() throws Exception {

		List<HttpMessageConverter<?>> converters = Arrays.asList(
				new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter());

		this.handler = new ResponseBodyEmitterReturnValueHandler(converters);
		this.request = new MockHttpServletRequest();
		this.response = new MockHttpServletResponse();
		this.webRequest = new ServletWebRequest(this.request, this.response);

		AsyncWebRequest asyncWebRequest = new StandardServletAsyncWebRequest(this.request, this.response);
		WebAsyncUtils.getAsyncManager(this.webRequest).setAsyncWebRequest(asyncWebRequest);
		this.request.setAsyncSupported(true);
	}
