	@Test
	public void testRootObject() throws Exception {
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

		//  The constructor arguments are name, birthday, and nationaltiy.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name");

		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setRootObject(tesla);

		String name = (String) exp.getValue(context);
		assertEquals("Nikola Tesla",name);
	}
