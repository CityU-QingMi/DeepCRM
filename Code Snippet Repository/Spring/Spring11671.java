	@Test
	public void getMatchingConditionWithEmptyConditions() throws Exception {
		RequestMethodsRequestCondition condition = new RequestMethodsRequestCondition();
		for (RequestMethod method : RequestMethod.values()) {
			if (!OPTIONS.equals(method)) {
				ServerWebExchange exchange = getExchange(method.name());
				assertNotNull(condition.getMatchingCondition(exchange));
			}
		}
		testNoMatch(condition, OPTIONS);
	}
