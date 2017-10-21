	@Before
	public void setup() {
		this.request = new MockHttpServletRequest();
		this.request.setRequestURI("/test.html");
		this.request.setRemoteHost("domain1.com");
		this.conf = new CorsConfiguration();
		this.response = new MockHttpServletResponse();
		this.response.setStatus(HttpServletResponse.SC_OK);
		this.processor = new DefaultCorsProcessor();
	}
