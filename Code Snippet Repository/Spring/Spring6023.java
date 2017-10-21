	@Test
	public void testVariables() throws Exception {
		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("newName", "Mike Tesla");

		context.setRootObject(tesla);

		parser.parseExpression("foo = #newName").getValue(context);

		assertEquals("Mike Tesla",tesla.getFoo());
	}
