	@Before
	public void setup() throws Exception {
		resolver = new ServletRequestMethodArgumentResolver();
		mavContainer = new ModelAndViewContainer();
		servletRequest = new MockHttpServletRequest("GET", "");
		webRequest = new ServletWebRequest(servletRequest, new MockHttpServletResponse());

		method = getClass().getMethod("supportedParams", ServletRequest.class, MultipartRequest.class,
				HttpSession.class, Principal.class, Locale.class, InputStream.class, Reader.class,
				WebRequest.class, TimeZone.class, ZoneId.class, HttpMethod.class, PushBuilder.class);
	}
