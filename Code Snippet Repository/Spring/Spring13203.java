	@Test
	public void multiple() throws Exception {
		initServletWithControllers(MultipleUriTemplateController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels/42;q=24/bookings/21-other;q=12");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());
		assertEquals("test-42-q24-21-other-q12", response.getContentAsString());
	}
