	@Test
	public void testInternalResourceViewResolverWithAttributes() throws Exception {
		MockServletContext sc = new MockServletContext();
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		wac.refresh();
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		Properties props = new Properties();
		props.setProperty("key1", "value1");
		vr.setAttributes(props);
		Map map = new HashMap();
		map.put("key2", new Integer(2));
		vr.setAttributesMap(map);
		vr.setApplicationContext(wac);

		View view = vr.resolveViewName("example1", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "example1", ((InternalResourceView) view).getUrl());
		Map attributes = ((InternalResourceView) view).getStaticAttributes();
		assertEquals("value1", attributes.get("key1"));
		assertEquals(new Integer(2), attributes.get("key2"));

		view = vr.resolveViewName("example2", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "example2", ((InternalResourceView) view).getUrl());
		attributes = ((InternalResourceView) view).getStaticAttributes();
		assertEquals("value1", attributes.get("key1"));
		assertEquals(new Integer(2), attributes.get("key2"));

		MockHttpServletRequest request = new MockHttpServletRequest(sc);
		HttpServletResponse response = new MockHttpServletResponse();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
		Map model = new HashMap();
		TestBean tb = new TestBean();
		model.put("tb", tb);
		view.render(model, request, response);

		assertTrue("Correct tb attribute", tb.equals(request.getAttribute("tb")));
		assertTrue("Correct rc attribute", request.getAttribute("rc") == null);
		assertEquals("value1", request.getAttribute("key1"));
		assertEquals(new Integer(2), request.getAttribute("key2"));
	}
