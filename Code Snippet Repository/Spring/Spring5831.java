	@Test
	public void incdecTogether() {
		Spr9751 helper = new Spr9751();
		StandardEvaluationContext ctx = new StandardEvaluationContext(helper);
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Expression e = null;

		// index1 is 2 at the start - the 'intArray[#root.index1++]' should not be evaluated twice!
		// intArray[2] is 3
		e = parser.parseExpression("intArray[#root.index1++]++");
		e.getValue(ctx,Integer.class);
		assertEquals(3,helper.index1);
		assertEquals(4,helper.intArray[2]);

		// index1 is 3 intArray[3] is 4
		e =  parser.parseExpression("intArray[#root.index1++]--");
		assertEquals(4,e.getValue(ctx,Integer.class).intValue());
		assertEquals(4,helper.index1);
		assertEquals(3,helper.intArray[3]);

		// index1 is 4, intArray[3] is 3
		e =  parser.parseExpression("intArray[--#root.index1]++");
		assertEquals(3,e.getValue(ctx,Integer.class).intValue());
		assertEquals(3,helper.index1);
		assertEquals(4,helper.intArray[3]);
	}
