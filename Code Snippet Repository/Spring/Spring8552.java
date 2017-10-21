	@Before
	public void setUp() {
		given(wac.getServletContext()).willReturn(mockServletContext);
		given(testContext.getApplicationContext()).willReturn(wac);

		MockHttpServletRequest request = new MockHttpServletRequest(mockServletContext);
		MockHttpServletResponse response = new MockHttpServletResponse();
		ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);

		request.setAttribute(SET_UP_OUTSIDE_OF_STEL, "true");

		RequestContextHolder.setRequestAttributes(servletWebRequest);
		assertSetUpOutsideOfStelAttributeExists();
	}
