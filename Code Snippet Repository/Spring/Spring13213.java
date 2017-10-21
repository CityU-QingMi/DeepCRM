	@Test
	public void methodNotSupported() throws Exception {
		initServletWithControllers(MethodNotAllowedController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels/1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());

		request = new MockHttpServletRequest("POST", "/hotels/1");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(405, response.getStatus());

		request = new MockHttpServletRequest("GET", "/hotels");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());

		request = new MockHttpServletRequest("POST", "/hotels");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(405, response.getStatus());
	}
