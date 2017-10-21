	@Test
	public void cacheExpression() {
		Method method = ReflectionUtils.findMethod(getClass(), "toString");

		expressionEvaluator.getTestExpression("true", method, getClass());
		expressionEvaluator.getTestExpression("true", method, getClass());
		expressionEvaluator.getTestExpression("true", method, getClass());
		hasParsedExpression("true");
		assertEquals("Only one expression should be in cache", 1, expressionEvaluator.testCache.size());
	}
