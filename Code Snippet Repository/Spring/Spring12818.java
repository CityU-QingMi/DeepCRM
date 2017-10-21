	@Test
	public void getNameExplicit() {

		Method method = ClassUtils.getMethod(TestController.class, "handle");
		HandlerMethod handlerMethod = new HandlerMethod(new TestController(), method);

		RequestMappingInfo rmi = new RequestMappingInfo("foo", null, null, null, null, null, null, null);

		HandlerMethodMappingNamingStrategy strategy = new RequestMappingInfoHandlerMethodMappingNamingStrategy();

		assertEquals("foo", strategy.getName(handlerMethod, rmi));
	}
