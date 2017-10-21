	@Test
	public void bestMatchWildcard() throws Exception {

		Method method = this.testController.getClass().getMethod("bestMatch");
		this.messageHandler.registerHandlerMethod(this.testController, method, "/bestmatch/{foo}/path");

		method = this.testController.getClass().getMethod("secondBestMatch");
		this.messageHandler.registerHandlerMethod(this.testController, method, "/bestmatch/*/*");

		this.messageHandler.handleMessage(toDestination("/test/bestmatch/bar/path"));

		assertEquals("bestMatch", this.testController.method);
	}
