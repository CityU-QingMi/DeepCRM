	@Test
	public void testInternalResourceViewResolverWithContextBeans() throws Exception {
		MockServletContext sc = new MockServletContext();
		final StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.registerSingleton("myBean", TestBean.class);
		wac.registerSingleton("myBean2", TestBean.class);
		wac.setServletContext(sc);
		wac.refresh();
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		Properties props = new Properties();
		props.setProperty("key1", "value1");
		vr.setAttributes(props);
		Map map = new HashMap();
		map.put("key2", new Integer(2));
		vr.setAttributesMap(map);
		vr.setExposeContextBeansAsAttributes(true);
		vr.setApplicationContext(wac);

		MockHttpServletRequest request = new MockHttpServletRequest(sc) {
			@Override
			public RequestDispatcher getRequestDispatcher(String path) {
				return new MockRequestDispatcher(path) {
					@Override
					public void forward(ServletRequest forwardRequest, ServletResponse forwardResponse) {
						assertTrue("Correct rc attribute", forwardRequest.getAttribute("rc") == null);
						assertEquals("value1", forwardRequest.getAttribute("key1"));
						assertEquals(new Integer(2), forwardRequest.getAttribute("key2"));
						assertSame(wac.getBean("myBean"), forwardRequest.getAttribute("myBean"));
						assertSame(wac.getBean("myBean2"), forwardRequest.getAttribute("myBean2"));
					}
				};
			}
		};
		HttpServletResponse response = new MockHttpServletResponse();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
		View view = vr.resolveViewName("example1", Locale.getDefault());
		view.render(new HashMap(), request, response);
	}
