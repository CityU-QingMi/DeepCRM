	@Test
	public void binding() throws Exception {
		initServletWithControllers(BindingUriTemplateController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels/42/dates/2008-11-18");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());

		request = new MockHttpServletRequest("GET", "/hotels/42/dates/2008-foo-bar");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(400, response.getStatus());

		initServletWithControllers(NonBindingUriTemplateController.class);
		request = new MockHttpServletRequest("GET", "/hotels/42/dates/2008-foo-bar");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(500, response.getStatus());
	}
