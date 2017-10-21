	@Test
	public void testScenario03_Arithmetic() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		// Might be better with a as a variable although it would work as a property too...
		// Variable references using a '#'
		Expression expr = parser.parseRaw("(hasRole('SUPERVISOR') or (#a <  1.042)) and hasIpAddress('10.10.0.0/16')");

		Boolean value = null;

		ctx.setVariable("a",1.0d); // referenced as #a in the expression
		ctx.setRootObject(new Supervisor("Ben")); // so non-qualified references 'hasRole()' 'hasIpAddress()' are invoked against it
		value = expr.getValue(ctx,Boolean.class);
		assertTrue(value);

		ctx.setRootObject(new Manager("Luke"));
		ctx.setVariable("a",1.043d);
		value = expr.getValue(ctx,Boolean.class);
		assertFalse(value);
	}
