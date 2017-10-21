	@Test
	public void SPR10146_malformedExpressions() throws Exception {
		doTestSpr10146("/foo", "EL1070E: Problem parsing left operand");
		doTestSpr10146("*foo", "EL1070E: Problem parsing left operand");
		doTestSpr10146("%foo", "EL1070E: Problem parsing left operand");
		doTestSpr10146("<foo", "EL1070E: Problem parsing left operand");
		doTestSpr10146(">foo", "EL1070E: Problem parsing left operand");
		doTestSpr10146("&&foo", "EL1070E: Problem parsing left operand");
		doTestSpr10146("||foo", "EL1070E: Problem parsing left operand");
		doTestSpr10146("&foo", "EL1069E: missing expected character '&'");
		doTestSpr10146("|foo", "EL1069E: missing expected character '|'");
	}
