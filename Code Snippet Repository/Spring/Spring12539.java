	@Test
	public void testHttpRequestHandlerServletPassThrough() throws Exception {
		MockServletContext servletContext = new MockServletContext();
		final MockHttpServletRequest request = new MockHttpServletRequest();
		final MockHttpServletResponse response = new MockHttpServletResponse();

		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.getBeanFactory().registerSingleton("myHandler", new HttpRequestHandler() {
			@Override
			public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				assertSame(request, req);
				assertSame(response, res);
				String exception = request.getParameter("exception");
				if ("ServletException".equals(exception)) {
					throw new ServletException("test");
				}
				if ("IOException".equals(exception)) {
					throw new IOException("test");
				}
				res.getWriter().write("myResponse");
			}
		});
		wac.setServletContext(servletContext);
		wac.refresh();
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		Servlet servlet = new HttpRequestHandlerServlet();
		servlet.init(new MockServletConfig(servletContext, "myHandler"));

		servlet.service(request, response);
		assertEquals("myResponse", response.getContentAsString());

		try {
			request.setParameter("exception", "ServletException");
			servlet.service(request, response);
			fail("Should have thrown ServletException");
		}
		catch (ServletException ex) {
			assertEquals("test", ex.getMessage());
		}

		try {
			request.setParameter("exception", "IOException");
			servlet.service(request, response);
			fail("Should have thrown IOException");
		}
		catch (IOException ex) {
			assertEquals("test", ex.getMessage());
		}
	}
