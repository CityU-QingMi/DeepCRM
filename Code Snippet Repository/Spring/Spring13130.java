	@Test
	public void prototypeController() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition beanDef = new RootBeanDefinition(PrototypeController.class);
			beanDef.setScope(BeanDefinition.SCOPE_PROTOTYPE);
			wac.registerBeanDefinition("controller", beanDef);
		});

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
		request.addParameter("param", "1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals("count:3", response.getContentAsString());

		response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals("count:3", response.getContentAsString());
	}
