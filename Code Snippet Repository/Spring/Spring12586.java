	@Test
	public void withNoViewAndSamePath() throws Exception {
		InternalResourceViewResolver vr = (InternalResourceViewResolver) complexDispatcherServlet
				.getWebApplicationContext().getBean("viewResolver2");
		vr.setSuffix("");

		MockServletContext servletContext = new MockServletContext();
		MockHttpServletRequest request = new MockHttpServletRequest(servletContext, "GET", "/noview");
		MockHttpServletResponse response = new MockHttpServletResponse();

		try {
			complexDispatcherServlet.service(request, response);
			fail("Should have thrown ServletException");
		}
		catch (ServletException ex) {
			ex.printStackTrace();
		}
	}
