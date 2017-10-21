	@Test
	public void positionalInformation() {
		SpelExpression expr = new SpelExpressionParser().parseRaw("true and true or false");
		SpelNode rootAst = expr.getAST();
		OpOr operatorOr = (OpOr) rootAst;
		OpAnd operatorAnd = (OpAnd) operatorOr.getLeftOperand();
		SpelNode rightOrOperand = operatorOr.getRightOperand();

		// check position for final 'false'
		assertEquals(17, rightOrOperand.getStartPosition());
		assertEquals(22, rightOrOperand.getEndPosition());

		// check position for first 'true'
		assertEquals(0, operatorAnd.getLeftOperand().getStartPosition());
		assertEquals(4, operatorAnd.getLeftOperand().getEndPosition());

		// check position for second 'true'
		assertEquals(9, operatorAnd.getRightOperand().getStartPosition());
		assertEquals(13, operatorAnd.getRightOperand().getEndPosition());

		// check position for OperatorAnd
		assertEquals(5, operatorAnd.getStartPosition());
		assertEquals(8, operatorAnd.getEndPosition());

		// check position for OperatorOr
		assertEquals(14, operatorOr.getStartPosition());
		assertEquals(16, operatorOr.getEndPosition());
	}
