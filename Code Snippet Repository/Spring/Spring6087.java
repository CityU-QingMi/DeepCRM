	@Test
	public void testNestedExpressions() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		// treat the nested ${..} as a part of the expression
		Expression ex = parser.parseExpression("hello ${listOfNumbersUpToTen.$[#this<5]} world",DEFAULT_TEMPLATE_PARSER_CONTEXT);
		String s = ex.getValue(TestScenarioCreator.getTestEvaluationContext(),String.class);
		assertEquals("hello 4 world",s);

		// not a useful expression but tests nested expression syntax that clashes with template prefix/suffix
		ex = parser.parseExpression("hello ${listOfNumbersUpToTen.$[#root.listOfNumbersUpToTen.$[#this%2==1]==3]} world",DEFAULT_TEMPLATE_PARSER_CONTEXT);
		assertEquals(CompositeStringExpression.class,ex.getClass());
		CompositeStringExpression cse = (CompositeStringExpression)ex;
		Expression[] exprs = cse.getExpressions();
		assertEquals(3,exprs.length);
		assertEquals("listOfNumbersUpToTen.$[#root.listOfNumbersUpToTen.$[#this%2==1]==3]",exprs[1].getExpressionString());
		s = ex.getValue(TestScenarioCreator.getTestEvaluationContext(),String.class);
		assertEquals("hello  world",s);

		ex = parser.parseExpression("hello ${listOfNumbersUpToTen.$[#this<5]} ${listOfNumbersUpToTen.$[#this>5]} world",DEFAULT_TEMPLATE_PARSER_CONTEXT);
		s = ex.getValue(TestScenarioCreator.getTestEvaluationContext(),String.class);
		assertEquals("hello 4 10 world",s);

		try {
			ex = parser.parseExpression("hello ${listOfNumbersUpToTen.$[#this<5]} ${listOfNumbersUpToTen.$[#this>5] world",DEFAULT_TEMPLATE_PARSER_CONTEXT);
			fail("Should have failed");
		}
		catch (ParseException pe) {
			assertEquals("No ending suffix '}' for expression starting at character 41: ${listOfNumbersUpToTen.$[#this>5] world", pe.getSimpleMessage());
		}

		try {
			ex = parser.parseExpression("hello ${listOfNumbersUpToTen.$[#root.listOfNumbersUpToTen.$[#this%2==1==3]} world",DEFAULT_TEMPLATE_PARSER_CONTEXT);
			fail("Should have failed");
		}
		catch (ParseException pe) {
			assertEquals("Found closing '}' at position 74 but most recent opening is '[' at position 30", pe.getSimpleMessage());
		}
	}
