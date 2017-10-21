	@Test
	public void handlerMappingJavaConfig() throws Exception {
		AnnotationConfigApplicationContext wac = new AnnotationConfigApplicationContext();
		wac.register(WebConfig.class);
		wac.refresh();

		HandlerMapping handlerMapping = (HandlerMapping) wac.getBean("handlerMapping");
		Object mainController = wac.getBean("mainController");
		Object otherController = wac.getBean("otherController");

		testUrl("/welcome.html", mainController, handlerMapping, "");
		testUrl("/welcome.x", otherController, handlerMapping, "welcome.x");
		testUrl("/welcome/", otherController, handlerMapping, "welcome");
		testUrl("/show.html", mainController, handlerMapping, "");
		testUrl("/bookseats.html", mainController, handlerMapping, "");
	}
