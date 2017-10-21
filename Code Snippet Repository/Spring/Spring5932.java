	@Test
	public void testScenario02_ComparingNames() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		ctx.addPropertyAccessor(new SecurityPrincipalAccessor());

		// Multiple options for supporting this expression: "p.name == principal.name"
		// (1) If the right person is the root context object then "name==principal.name" is good enough
		Expression expr = parser.parseRaw("name == principal.name");

		ctx.setRootObject(new Person("Andy"));
		Boolean value = expr.getValue(ctx,Boolean.class);
		assertTrue(value);

		ctx.setRootObject(new Person("Christian"));
		value = expr.getValue(ctx,Boolean.class);
		assertFalse(value);

		// (2) Or register an accessor that can understand 'p' and return the right person
		expr = parser.parseRaw("p.name == principal.name");

		PersonAccessor pAccessor = new PersonAccessor();
		ctx.addPropertyAccessor(pAccessor);
		ctx.setRootObject(null);

		pAccessor.setPerson(new Person("Andy"));
		value = expr.getValue(ctx,Boolean.class);
		assertTrue(value);

		pAccessor.setPerson(new Person("Christian"));
		value = expr.getValue(ctx,Boolean.class);
		assertFalse(value);
	}
