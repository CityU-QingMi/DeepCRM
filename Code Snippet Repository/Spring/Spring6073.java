	@Test
	@SuppressWarnings("")
	public void SPR13055() throws Exception {
		List<Map<String, Object>> myPayload = new ArrayList<>();

		Map<String, Object> v1 = new HashMap<>();
		Map<String, Object> v2 = new HashMap<>();

		v1.put("test11", "test11");
		v1.put("test12", "test12");
		v2.put("test21", "test21");
		v2.put("test22", "test22");

		myPayload.add(v1);
		myPayload.add(v2);

		EvaluationContext context = new StandardEvaluationContext(myPayload);

		ExpressionParser parser = new SpelExpressionParser();

		String ex = "#root.![T(org.springframework.util.StringUtils).collectionToCommaDelimitedString(#this.values())]";
		List res = parser.parseExpression(ex).getValue(context, List.class);
		assertEquals("[test12,test11, test22,test21]", res.toString());

		res = parser.parseExpression("#root.![#this.values()]").getValue(context,
				List.class);
		assertEquals("[[test12, test11], [test22, test21]]", res.toString());

		res = parser.parseExpression("#root.![values()]").getValue(context, List.class);
		assertEquals("[[test12, test11], [test22, test21]]", res.toString());
	}
