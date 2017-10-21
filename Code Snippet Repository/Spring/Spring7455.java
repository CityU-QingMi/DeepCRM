	@Test
	public void antPatchMatchWildcard() throws Exception {

		Method method = this.testController.getClass().getMethod("handlerPathMatchWildcard");
		this.messageHandler.registerHandlerMethod(this.testController, method, "/handlerPathMatch*");

		this.messageHandler.handleMessage(toDestination("/test/handlerPathMatchFoo"));

		assertEquals("pathMatchWildcard", this.testController.method);
	}
