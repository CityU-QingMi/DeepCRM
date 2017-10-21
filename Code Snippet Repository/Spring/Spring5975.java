	@Test
	public void methodReferenceMissingCastAndRootObjectAccessing_SPR12326() {
		// Need boxing code on the 1 so that toString() can be called
		expression = parser.parseExpression("1.toString()");
		assertEquals("1", expression.getValue());
		assertCanCompile(expression);
		assertEquals("1", expression.getValue());

		expression = parser.parseExpression("#it?.age.equals([0])");
		Person person = new Person(1);
		StandardEvaluationContext context = new StandardEvaluationContext(new Object[] {person.getAge()});
		context.setVariable("it", person);
		assertTrue(expression.getValue(context, Boolean.class));
		assertCanCompile(expression);
		assertTrue(expression.getValue(context, Boolean.class));

		// Variant of above more like what was in the bug report:
		SpelExpressionParser parser = new SpelExpressionParser(
				new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, getClass().getClassLoader()));

		SpelExpression ex = parser.parseRaw("#it?.age.equals([0])");
		context = new StandardEvaluationContext(new Object[] {person.getAge()});
		context.setVariable("it", person);
		assertTrue(ex.getValue(context, Boolean.class));
		assertTrue(ex.getValue(context, Boolean.class));

		PersonInOtherPackage person2 = new PersonInOtherPackage(1);
		ex = parser.parseRaw("#it?.age.equals([0])");
		context = new StandardEvaluationContext(new Object[] {person2.getAge()});
		context.setVariable("it", person2);
		assertTrue(ex.getValue(context, Boolean.class));
		assertTrue(ex.getValue(context, Boolean.class));

		ex = parser.parseRaw("#it?.age.equals([0])");
		context = new StandardEvaluationContext(new Object[] {person2.getAge()});
		context.setVariable("it", person2);
		assertTrue((Boolean) ex.getValue(context));
		assertTrue((Boolean) ex.getValue(context));
	}
