	protected MockPageContext createPageContext() {
		MockServletContext sc = new MockServletContext();
		SimpleWebApplicationContext wac = new SimpleWebApplicationContext();
		wac.setServletContext(sc);
		wac.setNamespace("test");
		wac.refresh();

		MockHttpServletRequest request = new MockHttpServletRequest(sc);
		MockHttpServletResponse response = new MockHttpServletResponse();
		if (inDispatcherServlet()) {
			request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
			LocaleResolver lr = new AcceptHeaderLocaleResolver();
			request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, lr);
			ThemeResolver tr = new FixedThemeResolver();
			request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, tr);
			request.setAttribute(DispatcherServlet.THEME_SOURCE_ATTRIBUTE, wac);
		}
		else {
			sc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		}

		return new MockPageContext(sc, request, response);
	}
