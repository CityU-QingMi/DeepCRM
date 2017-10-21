	@Test
	public void initializingCollectionElementsOnWrite() throws Exception {
		TestPerson person = new TestPerson();
		EvaluationContext context = new StandardEvaluationContext(person);
		SpelParserConfiguration config = new SpelParserConfiguration(true, true);
		ExpressionParser parser = new SpelExpressionParser(config);
		Expression expression = parser.parseExpression("name");
		expression.setValue(context, "Oleg");
		assertEquals("Oleg", person.getName());

		expression = parser.parseExpression("address.street");
		expression.setValue(context, "123 High St");
		assertEquals("123 High St", person.getAddress().getStreet());

		expression = parser.parseExpression("address.crossStreets[0]");
		expression.setValue(context, "Blah");
		assertEquals("Blah", person.getAddress().getCrossStreets().get(0));

		expression = parser.parseExpression("address.crossStreets[3]");
		expression.setValue(context, "Wibble");
		assertEquals("Blah", person.getAddress().getCrossStreets().get(0));
		assertEquals("Wibble", person.getAddress().getCrossStreets().get(3));
	}
