	@Test
	public void testInternalResourceViewResolverWithJstlAndContextParam() throws Exception {
		Locale locale = !Locale.GERMAN.equals(Locale.getDefault()) ? Locale.GERMAN : Locale.FRENCH;

		MockServletContext sc = new MockServletContext();
		sc.addInitParameter(Config.FMT_LOCALIZATION_CONTEXT, "org/springframework/web/context/WEB-INF/context-messages");
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		wac.addMessage("code1", locale, "messageX");
		wac.refresh();
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setViewClass(JstlView.class);
		vr.setApplicationContext(wac);

		View view = vr.resolveViewName("example1", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "example1", ((JstlView) view).getUrl());

		view = vr.resolveViewName("example2", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "example2", ((JstlView) view).getUrl());

		MockHttpServletRequest request = new MockHttpServletRequest(sc);
		HttpServletResponse response = new MockHttpServletResponse();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new FixedLocaleResolver(locale));
		Map model = new HashMap();
		TestBean tb = new TestBean();
		model.put("tb", tb);
		view.render(model, request, response);

		assertTrue("Correct tb attribute", tb.equals(request.getAttribute("tb")));
		assertTrue("Correct rc attribute", request.getAttribute("rc") == null);

		assertEquals(locale, Config.get(request, Config.FMT_LOCALE));
		LocalizationContext lc = (LocalizationContext) Config.get(request, Config.FMT_LOCALIZATION_CONTEXT);
		assertEquals("message1", lc.getResourceBundle().getString("code1"));
		assertEquals("message2", lc.getResourceBundle().getString("code2"));
	}
