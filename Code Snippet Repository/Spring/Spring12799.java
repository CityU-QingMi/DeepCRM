	@Test
	public void getMatchingConditionWithEmptyConditions() {
		RequestMethodsRequestCondition condition = new RequestMethodsRequestCondition();
		for (RequestMethod method : RequestMethod.values()) {
			if (!OPTIONS.equals(method)) {
				HttpServletRequest request = new MockHttpServletRequest(method.name(), "");
				assertNotNull(condition.getMatchingCondition(request));
			}
		}
		testNoMatch(condition, OPTIONS);
	}
