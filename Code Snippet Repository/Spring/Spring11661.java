	@Test
	public void match() {
		RequestMethodsRequestCondition rm = new RequestMethodsRequestCondition(RequestMethod.GET, RequestMethod.POST);
		RequestConditionHolder custom = new RequestConditionHolder(rm);
		RequestMethodsRequestCondition expected = new RequestMethodsRequestCondition(RequestMethod.GET);

		RequestConditionHolder holder = custom.getMatchingCondition(this.exchange);
		assertNotNull(holder);
		assertEquals(expected,  holder.getCondition());
	}
