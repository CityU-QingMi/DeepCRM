	@Test
	public void testConstructors() throws Exception {
		StandardEvaluationContext societyContext = new StandardEvaluationContext();
		societyContext.setRootObject(new IEEE());
		Inventor einstein =
			   parser.parseExpression("new org.springframework.expression.spel.testresources.Inventor('Albert Einstein',new java.util.Date(), 'German')").getValue(Inventor.class);
		assertEquals("Albert Einstein", einstein.getName());
		//create new inventor instance within add method of List
		parser.parseExpression("Members2.add(new org.springframework.expression.spel.testresources.Inventor('Albert Einstein', 'German'))").getValue(societyContext);
	}
