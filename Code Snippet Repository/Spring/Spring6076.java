	@Test
	public void SPR13055_maps() {
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		Expression ex = parser.parseExpression("{'a':'y','b':'n'}.![value=='y'?key:null]");
		assertEquals("[a, null]", ex.getValue(context).toString());

		ex = parser.parseExpression("{2:4,3:6}.![T(java.lang.Math).abs(#this.key) + 5]");
		assertEquals("[7, 8]", ex.getValue(context).toString());

		ex = parser.parseExpression("{2:4,3:6}.![T(java.lang.Math).abs(#this.value) + 5]");
		assertEquals("[9, 11]", ex.getValue(context).toString());
	}
