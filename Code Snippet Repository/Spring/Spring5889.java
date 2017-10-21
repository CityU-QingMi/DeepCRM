	@Test
	public void testGetValuePerformance() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);
		Map<String, String> map = new HashMap<>();
		map.put("key", "value");
		EvaluationContext context = new StandardEvaluationContext(map);

		ExpressionParser spelExpressionParser = new SpelExpressionParser();
		Expression expr = spelExpressionParser.parseExpression("#root['key']");

		StopWatch s = new StopWatch();
		s.start();
		for (int i = 0; i < 10000; i++) {
			expr.getValue(context);
		}
		s.stop();
		assertThat(s.getTotalTimeMillis(), lessThan(200L));
	}
