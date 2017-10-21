	@Test
	public void getNameConvention() {

		Method method = ClassUtils.getMethod(TestController.class, "handle");
		HandlerMethod handlerMethod = new HandlerMethod(new TestController(), method);

		RequestMappingInfo rmi = new RequestMappingInfo(null, null, null, null, null, null, null, null);

		HandlerMethodMappingNamingStrategy strategy = new RequestMappingInfoHandlerMethodMappingNamingStrategy();

		assertEquals("TC#handle", strategy.getName(handlerMethod, rmi));
	}
