	@Test
	public void getMatchingConditionWithHttpOptionsInErrorDispatch() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/path");
		request.setDispatcherType(DispatcherType.ERROR);

		RequestMethodsRequestCondition condition = new RequestMethodsRequestCondition();
		RequestMethodsRequestCondition result = condition.getMatchingCondition(request);

		assertNotNull(result);
		assertSame(condition, result);
	}
