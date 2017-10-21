	@Test
	public void supports() throws NoSuchMethodException {
		Object controller = new TestController();
		Method method;

		method = on(TestController.class).annotPresent(ResponseBody.class).resolveMethod();
		testSupports(controller, method);

		method = on(TestController.class).annotNotPresent(ResponseBody.class).resolveMethod("doWork");
		HandlerResult handlerResult = getHandlerResult(controller, method);
		assertFalse(this.resultHandler.supports(handlerResult));
	}
