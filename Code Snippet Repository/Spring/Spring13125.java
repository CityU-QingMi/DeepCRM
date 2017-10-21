	@Test
	public void defaultExpressionParameters() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition ppc = new RootBeanDefinition(PropertyPlaceholderConfigurer.class);
			ppc.getPropertyValues().add("properties", "myKey=foo");
			wac.registerBeanDefinition("ppc", ppc);
		}, DefaultExpressionValueParamController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myApp/myPath.do");
		request.setContextPath("/myApp");
		MockHttpServletResponse response = new MockHttpServletResponse();
		System.setProperty("myHeader", "bar");
		try {
			getServlet().service(request, response);
		}
		finally {
			System.clearProperty("myHeader");
		}
		assertEquals("foo-bar-/myApp", response.getContentAsString());
	}
