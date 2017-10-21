	@Test
	public void functionReferenceNonCompilableArguments_SPR12359() throws Exception {
		StandardEvaluationContext context = new StandardEvaluationContext(new Object[] {"1"});
		context.registerFunction("negate", SomeCompareMethod2.class.getDeclaredMethod(
				"negate", Integer.TYPE));
		context.setVariable("arg", "2");
		int[] ints = new int[] {1,2,3};
		context.setVariable("ints",ints);

		expression = parser.parseExpression("#negate(#ints.?[#this<2][0])");
		assertEquals("-1", expression.getValue(context, Integer.class).toString());
		// Selection isn't compilable.
		assertFalse(((SpelNodeImpl)((SpelExpression) expression).getAST()).isCompilable());
	}
