	private void doTestServletForwardingController(ServletForwardingController sfc, boolean include)
			throws Exception {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		ServletContext context = mock(ServletContext.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		given(request.getMethod()).willReturn("GET");
		given(context.getNamedDispatcher("action")).willReturn(dispatcher);
		if (include) {
			given(request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE)).willReturn("somePath");
		}
		else {
			given(request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE)).willReturn(null);
		}

		StaticWebApplicationContext sac = new StaticWebApplicationContext();
		sac.setServletContext(context);
		sfc.setApplicationContext(sac);
		assertNull(sfc.handleRequest(request, response));

		if (include) {
			verify(dispatcher).include(request, response);
		}
		else {
			verify(dispatcher).forward(request, response);
		}
	}
