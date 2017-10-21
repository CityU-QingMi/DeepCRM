	@Test
	public void greaterThanWithNulls_SPR7840() throws Exception {
		List<D> list = new ArrayList<>();
		list.add(new D("aaa"));
		list.add(new D("bbb"));
		list.add(new D(null));
		list.add(new D("ccc"));
		list.add(new D(null));
		list.add(new D("zzz"));

		StandardEvaluationContext ctx = new StandardEvaluationContext(list);
		SpelExpressionParser parser = new SpelExpressionParser();

		String el1 = "#root.?[a < 'hhh']";
		SpelExpression exp = parser.parseRaw(el1);
		Object value = exp.getValue(ctx);
		assertEquals("[D(aaa), D(bbb), D(null), D(ccc), D(null)]", value.toString());

		String el2 = "#root.?[a > 'hhh']";
		SpelExpression exp2 = parser.parseRaw(el2);
		Object value2 = exp2.getValue(ctx);
		assertEquals("[D(zzz)]", value2.toString());

		// trim out the nulls first
		String el3 = "#root.?[a!=null].?[a < 'hhh']";
		SpelExpression exp3 = parser.parseRaw(el3);
		Object value3 = exp3.getValue(ctx);
		assertEquals("[D(aaa), D(bbb), D(ccc)]", value3.toString());
	}
