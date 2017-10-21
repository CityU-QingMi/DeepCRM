	@Test
	public void SPR5905_InnerTypeReferences() throws Exception {
		StandardEvaluationContext eContext = new StandardEvaluationContext(new Spr5899Class());
		Expression expr = new SpelExpressionParser().parseRaw("T(java.util.Map$Entry)");
		assertEquals(Map.Entry.class, expr.getValue(eContext));

		expr = new SpelExpressionParser().parseRaw("T(org.springframework.expression.spel.SpelReproTests$Outer$Inner).run()");
		assertEquals(12, expr.getValue(eContext));

		expr = new SpelExpressionParser().parseRaw("new org.springframework.expression.spel.SpelReproTests$Outer$Inner().run2()");
		assertEquals(13, expr.getValue(eContext));
	}
