	private void registerMvcSingletons(StubWebApplicationContext wac) {
		StandaloneConfiguration config = new StandaloneConfiguration();
		config.setApplicationContext(wac);
		ServletContext sc = wac.getServletContext();

		wac.addBeans(this.controllers);
		wac.addBeans(this.controllerAdvice);

		RequestMappingHandlerMapping hm = config.getHandlerMapping();
		if (sc != null) {
			hm.setServletContext(sc);
		}
		hm.setApplicationContext(wac);
		hm.afterPropertiesSet();
		wac.addBean("requestMappingHandlerMapping", hm);

		RequestMappingHandlerAdapter ha = config.requestMappingHandlerAdapter();
		if (sc != null) {
			ha.setServletContext(sc);
		}
		ha.setApplicationContext(wac);
		ha.afterPropertiesSet();
		wac.addBean("requestMappingHandlerAdapter", ha);

		wac.addBean("handlerExceptionResolver", config.handlerExceptionResolver());

		wac.addBeans(initViewResolvers(wac));
		wac.addBean(DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME, this.localeResolver);
		wac.addBean(DispatcherServlet.THEME_RESOLVER_BEAN_NAME, new FixedThemeResolver());
		wac.addBean(DispatcherServlet.REQUEST_TO_VIEW_NAME_TRANSLATOR_BEAN_NAME, new DefaultRequestToViewNameTranslator());

		this.flashMapManager = new SessionFlashMapManager();
		wac.addBean(DispatcherServlet.FLASH_MAP_MANAGER_BEAN_NAME, this.flashMapManager);
	}
