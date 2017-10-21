	@Test
	public void testRequestMappingMethod() throws Exception {
		String datePattern = "MM:dd:yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String dateA = "11:01:2011";
		String dateB = "11:02:2011";

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/path1/path2");
		request.setParameter("datePattern", datePattern);
		request.addHeader("header1", dateA);
		request.addHeader("header2", dateB);

		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		assertNotNull(chain);

		ModelAndView mav = handlerAdapter.handle(request, new MockHttpServletResponse(), chain.getHandler());

		assertEquals("model attr1:", dateFormat.parse(dateA), mav.getModel().get("attr1"));
		assertEquals("model attr2:", dateFormat.parse(dateB), mav.getModel().get("attr2"));

		MockHttpServletResponse response = new MockHttpServletResponse();
		exceptionResolver.resolveException(request, response, chain.getHandler(), new Exception("failure"));
		assertEquals("text/plain;charset=ISO-8859-1", response.getHeader("Content-Type"));
		assertEquals("failure", response.getContentAsString());
	}
