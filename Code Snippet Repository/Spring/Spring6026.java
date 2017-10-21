	@Test
	public void testTernary() throws Exception {
		String falseString = parser.parseExpression("false ? 'trueExp' : 'falseExp'").getValue(String.class);
		assertEquals("falseExp",falseString);

		StandardEvaluationContext societyContext = new StandardEvaluationContext();
		societyContext.setRootObject(new IEEE());


		parser.parseExpression("Name").setValue(societyContext, "IEEE");
		societyContext.setVariable("queryName", "Nikola Tesla");

		String expression = "isMember(#queryName)? #queryName + ' is a member of the ' "
				+ "+ Name + ' Society' : #queryName + ' is not a member of the ' + Name + ' Society'";

		String queryResultString = parser.parseExpression(expression).getValue(societyContext, String.class);
		assertEquals("Nikola Tesla is a member of the IEEE Society",queryResultString);
		// queryResultString = "Nikola Tesla is a member of the IEEE Society"
	}
