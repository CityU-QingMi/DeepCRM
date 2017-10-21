	private void doTestUrlBasedViewResolverWithoutPrefixes(UrlBasedViewResolver vr) throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		wac.refresh();
		vr.setApplicationContext(wac);
		vr.setContentType("myContentType");
		vr.setRequestContextAttribute("rc");

		View view = vr.resolveViewName("example1", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "example1", ((InternalResourceView) view).getUrl());
		assertEquals("Correct textContentType", "myContentType", ((InternalResourceView) view).getContentType());

		view = vr.resolveViewName("example2", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "example2", ((InternalResourceView) view).getUrl());
		assertEquals("Correct textContentType", "myContentType", ((InternalResourceView) view).getContentType());

		HttpServletRequest request = new MockHttpServletRequest(wac.getServletContext());
		HttpServletResponse response = new MockHttpServletResponse();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
		request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
		Map model = new HashMap();
		TestBean tb = new TestBean();
		model.put("tb", tb);
		view.render(model, request, response);
		assertTrue("Correct tb attribute", tb.equals(request.getAttribute("tb")));
		assertTrue("Correct rc attribute", request.getAttribute("rc") instanceof RequestContext);

		view = vr.resolveViewName("redirect:myUrl", Locale.getDefault());
		assertEquals("Correct view class", RedirectView.class, view.getClass());
		assertEquals("Correct URL", "myUrl", ((RedirectView) view).getUrl());
		assertSame("View not initialized as bean", wac, ((RedirectView) view).getApplicationContext());

		view = vr.resolveViewName("forward:myUrl", Locale.getDefault());
		assertEquals("Correct view class", InternalResourceView.class, view.getClass());
		assertEquals("Correct URL", "myUrl", ((InternalResourceView) view).getUrl());
	}
