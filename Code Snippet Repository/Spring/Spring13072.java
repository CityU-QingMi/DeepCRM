	@SuppressWarnings("")
	@Test
	public void sessionAttributeExposure() throws Exception {
		initServlet(
				wac -> wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(ModelExposingViewResolver.class)),
				MySessionAttributesController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPage");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("page1", request.getAttribute("viewName"));
		HttpSession session = request.getSession();
		assertTrue(session.getAttribute("object1") != null);
		assertTrue(session.getAttribute("object2") != null);
		assertTrue(((Map) session.getAttribute("model")).containsKey("object1"));
		assertTrue(((Map) session.getAttribute("model")).containsKey("object2"));

		request = new MockHttpServletRequest("POST", "/myPage");
		request.setSession(session);
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("page2", request.getAttribute("viewName"));
		assertTrue(session.getAttribute("object1") != null);
		assertTrue(session.getAttribute("object2") != null);
		assertTrue(((Map) session.getAttribute("model")).containsKey("object1"));
		assertTrue(((Map) session.getAttribute("model")).containsKey("object2"));
	}
