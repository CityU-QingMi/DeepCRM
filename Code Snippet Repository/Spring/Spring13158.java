	@Test
	public void methodNotAllowed() throws Exception {
		initServletWithControllers(MethodNotAllowedController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("Invalid response status", HttpServletResponse.SC_METHOD_NOT_ALLOWED, response.getStatus());
		String allowHeader = response.getHeader("Allow");
		assertNotNull("No Allow header", allowHeader);
		Set<String> allowedMethods = new HashSet<>();
		allowedMethods.addAll(Arrays.asList(StringUtils.delimitedListToStringArray(allowHeader, ", ")));
		assertEquals("Invalid amount of supported methods", 6, allowedMethods.size());
		assertTrue("PUT not allowed", allowedMethods.contains("PUT"));
		assertTrue("DELETE not allowed", allowedMethods.contains("DELETE"));
		assertTrue("HEAD not allowed", allowedMethods.contains("HEAD"));
		assertTrue("TRACE not allowed", allowedMethods.contains("TRACE"));
		assertTrue("OPTIONS not allowed", allowedMethods.contains("OPTIONS"));
		assertTrue("POST not allowed", allowedMethods.contains("POST"));
	}
