	@Test
	public void formController() throws Exception {
		initServlet(
				wac -> wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(TestViewResolver.class)),
				MyFormController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		request.addParameter("name", "name1");
		request.addParameter("age", "value2");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView-name1-typeMismatch-tb1-myValue", response.getContentAsString());
	}
