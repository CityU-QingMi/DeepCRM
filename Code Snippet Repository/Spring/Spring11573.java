	@Test
	public void handlerMappingXmlConfig() throws Exception {
		ClassPathXmlApplicationContext wac = new ClassPathXmlApplicationContext("map.xml", getClass());
		wac.refresh();

		HandlerMapping handlerMapping = wac.getBean("mapping", HandlerMapping.class);
		Object mainController = wac.getBean("mainController");

		testUrl("/pathmatchingTest.html", mainController, handlerMapping, "pathmatchingTest.html");
		testUrl("welcome.html", null, handlerMapping, null);
		testUrl("/pathmatchingAA.html", mainController, handlerMapping, "pathmatchingAA.html");
		testUrl("/pathmatchingA.html", null, handlerMapping, null);
		testUrl("/administrator/pathmatching.html", mainController, handlerMapping, "");
		testUrl("/administrator/test/pathmatching.html", mainController, handlerMapping, "test/pathmatching.html");
		testUrl("/administratort/pathmatching.html", null, handlerMapping, null);
		testUrl("/administrator/another/bla.xml", mainController, handlerMapping, "");
		testUrl("/administrator/another/bla.gif", null, handlerMapping, null);
		testUrl("/administrator/test/testlastbit", mainController, handlerMapping, "test/testlastbit");
		testUrl("/administrator/test/testla", null, handlerMapping, null);
		testUrl("/administrator/testing/longer/bla", mainController, handlerMapping, "bla");
		testUrl("/administrator/testing/longer2/notmatching/notmatching", null, handlerMapping, null);
		testUrl("/shortpattern/testing/toolong", null, handlerMapping, null);
		testUrl("/XXpathXXmatching.html", mainController, handlerMapping, "XXpathXXmatching.html");
		testUrl("/pathXXmatching.html", mainController, handlerMapping, "pathXXmatching.html");
		testUrl("/XpathXXmatching.html", null, handlerMapping, null);
		testUrl("/XXpathmatching.html", null, handlerMapping, null);
		testUrl("/show12.html", mainController, handlerMapping, "show12.html");
		testUrl("/show123.html", mainController, handlerMapping, "");
		testUrl("/show1.html", mainController, handlerMapping, "show1.html");
		testUrl("/reallyGood-test-is-this.jpeg", mainController, handlerMapping, "reallyGood-test-is-this.jpeg");
		testUrl("/reallyGood-tst-is-this.jpeg", null, handlerMapping, null);
		testUrl("/testing/test.jpeg", mainController, handlerMapping, "testing/test.jpeg");
		testUrl("/testing/test.jpg", null, handlerMapping, null);
		testUrl("/anotherTest", mainController, handlerMapping, "anotherTest");
		testUrl("/stillAnotherTest", null, handlerMapping, null);
		testUrl("outofpattern*ye", null, handlerMapping, null);
	}
