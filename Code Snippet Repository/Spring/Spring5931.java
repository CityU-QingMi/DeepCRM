	@Test
	public void testScenario01_Roles() throws Exception {
		try {
			SpelExpressionParser parser = new SpelExpressionParser();
			StandardEvaluationContext ctx = new StandardEvaluationContext();
			Expression expr = parser.parseRaw("hasAnyRole('MANAGER','TELLER')");

			ctx.setRootObject(new Person("Ben"));
			Boolean value = expr.getValue(ctx,Boolean.class);
			assertFalse(value);

			ctx.setRootObject(new Manager("Luke"));
			value = expr.getValue(ctx,Boolean.class);
			assertTrue(value);

		}
		catch (EvaluationException ee) {
			ee.printStackTrace();
			fail("Unexpected SpelException: " + ee.getMessage());
		}
	}
