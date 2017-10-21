	@Test
	public void match() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
		request.setParameter("param1", "paramValue1");
		request.addHeader("header1", "headerValue1");

		RequestCondition<?> getPostCond = new RequestMethodsRequestCondition(RequestMethod.GET, RequestMethod.POST);
		RequestCondition<?> getCond = new RequestMethodsRequestCondition(RequestMethod.GET);

		CompositeRequestCondition condition = new CompositeRequestCondition(this.param1, getPostCond);
		CompositeRequestCondition matchingCondition = new CompositeRequestCondition(this.param1, getCond);

		assertEquals(matchingCondition, condition.getMatchingCondition(request));
	}
