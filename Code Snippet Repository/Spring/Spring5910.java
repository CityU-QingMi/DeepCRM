	@Test
	public void testOperatorNames() throws Exception {
		Operator node = getOperatorNode((SpelExpression)parser.parseExpression("1==3"));
		assertEquals("==",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("1!=3"));
		assertEquals("!=",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3/3"));
		assertEquals("/",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3+3"));
		assertEquals("+",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3-3"));
		assertEquals("-",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3<4"));
		assertEquals("<",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3<=4"));
		assertEquals("<=",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3*4"));
		assertEquals("*",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3%4"));
		assertEquals("%",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3>=4"));
		assertEquals(">=",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3 between 4"));
		assertEquals("between",node.getOperatorName());

		node = getOperatorNode((SpelExpression)parser.parseExpression("3 ^ 4"));
		assertEquals("^",node.getOperatorName());
	}
