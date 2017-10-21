	@Test
	public void testMethodInvocation2() throws Exception {
		// string literal, evaluates to "bc"
		String c = parser.parseExpression("'abc'.substring(1, 3)").getValue(String.class);
		assertEquals("bc",c);

		StandardEvaluationContext societyContext = new StandardEvaluationContext();
		societyContext.setRootObject(new IEEE());
		// evaluates to true
		boolean isMember = parser.parseExpression("isMember('Mihajlo Pupin')").getValue(societyContext, Boolean.class);
		assertTrue(isMember);
	}
