	@Test
	public void SPR10486() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		Spr10486 rootObject = new Spr10486();
		Expression classNameExpression = parser.parseExpression("class.name");
		Expression nameExpression = parser.parseExpression("name");
		assertThat(classNameExpression.getValue(context, rootObject), equalTo((Object) Spr10486.class.getName()));
		assertThat(nameExpression.getValue(context, rootObject), equalTo((Object) "name"));
	}
