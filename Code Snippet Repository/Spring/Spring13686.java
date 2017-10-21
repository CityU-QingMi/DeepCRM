	private void doTestUrlBasedViewResolverWithPrefixes(UrlBasedViewResolver vr) throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		wac.refresh();
		vr.setPrefix("/WEB-INF/");
		vr.setSuffix(".jsp");
		vr.setApplicationContext(wac);

		View view = vr.resolveViewName("example1", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "/WEB-INF/example1.jsp", ((InternalResourceView) view).getUrl());

		view = vr.resolveViewName("example2", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "/WEB-INF/example2.jsp", ((InternalResourceView) view).getUrl());

		view = vr.resolveViewName("redirect:myUrl", Locale.getDefault());
		assertEquals("Correct view class", RedirectView.class, view.getClass());
		assertEquals("Correct URL", "myUrl", ((RedirectView) view).getUrl());

		view = vr.resolveViewName("forward:myUrl", Locale.getDefault());
		assertEquals("Correct view class", InternalResourceView.class, view.getClass());
		assertEquals("Correct URL", "myUrl", ((InternalResourceView) view).getUrl());
	}
