	@Test
	public void SPR10452() throws Exception {
		SpelParserConfiguration configuration = new SpelParserConfiguration(false, false);
		ExpressionParser parser = new SpelExpressionParser(configuration);

		StandardEvaluationContext context = new StandardEvaluationContext();
		Expression spel = parser.parseExpression("#enumType.values()");

		context.setVariable("enumType", ABC.class);
		Object result = spel.getValue(context);
		assertNotNull(result);
		assertTrue(result.getClass().isArray());
		assertEquals(ABC.A, Array.get(result, 0));
		assertEquals(ABC.B, Array.get(result, 1));
		assertEquals(ABC.C, Array.get(result, 2));

		context.setVariable("enumType", XYZ.class);
		result = spel.getValue(context);
		assertNotNull(result);
		assertTrue(result.getClass().isArray());
		assertEquals(XYZ.X, Array.get(result, 0));
		assertEquals(XYZ.Y, Array.get(result, 1));
		assertEquals(XYZ.Z, Array.get(result, 2));
	}
