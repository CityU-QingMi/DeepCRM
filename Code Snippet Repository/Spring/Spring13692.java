	@Test
	public void testXmlViewResolver() throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.registerSingleton("testBean", TestBean.class);
		wac.setServletContext(new MockServletContext());
		wac.refresh();
		TestBean testBean = (TestBean) wac.getBean("testBean");
		XmlViewResolver vr = new XmlViewResolver();
		vr.setLocation(new ClassPathResource("org/springframework/web/servlet/view/views.xml"));
		vr.setApplicationContext(wac);

		View view1 = vr.resolveViewName("example1", Locale.getDefault());
		assertTrue("Correct view class", TestView.class.equals(view1.getClass()));
		assertTrue("Correct URL", "/example1.jsp".equals(((InternalResourceView) view1).getUrl()));

		View view2 = vr.resolveViewName("example2", Locale.getDefault());
		assertTrue("Correct view class", JstlView.class.equals(view2.getClass()));
		assertTrue("Correct URL", "/example2new.jsp".equals(((InternalResourceView) view2).getUrl()));

		ServletContext sc = new MockServletContext();
		Map model = new HashMap();
		TestBean tb = new TestBean();
		model.put("tb", tb);

		HttpServletRequest request = new MockHttpServletRequest(sc);
		HttpServletResponse response = new MockHttpServletResponse();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
		request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
		view1.render(model, request, response);
		assertTrue("Correct tb attribute", tb.equals(request.getAttribute("tb")));
		assertTrue("Correct test1 attribute", "testvalue1".equals(request.getAttribute("test1")));
		assertTrue("Correct test2 attribute", testBean.equals(request.getAttribute("test2")));

		request = new MockHttpServletRequest(sc);
		response = new MockHttpServletResponse();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
		request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
		view2.render(model, request, response);
		assertTrue("Correct tb attribute", tb.equals(request.getAttribute("tb")));
		assertTrue("Correct test1 attribute", "testvalue1".equals(request.getAttribute("test1")));
		assertTrue("Correct test2 attribute", "testvalue2".equals(request.getAttribute("test2")));
	}
