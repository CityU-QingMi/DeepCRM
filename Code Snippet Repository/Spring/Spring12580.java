	@Test
	public void requestHandledEvent() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/locale.do");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		ComplexWebApplicationContext.TestApplicationListener listener =
				(ComplexWebApplicationContext.TestApplicationListener) complexDispatcherServlet
						.getWebApplicationContext().getBean("testListener");
		assertEquals(1, listener.counter);
	}
