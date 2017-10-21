	@Test
	public void limitCollectionGrowing() throws Exception {
		TestClass instance = new TestClass();
		StandardEvaluationContext ctx = new StandardEvaluationContext(instance);
		SpelExpressionParser parser = new SpelExpressionParser( new SpelParserConfiguration(true, true, 3));
		Expression expression = parser.parseExpression("foo[2]");
		expression.setValue(ctx, "2");
		assertThat(instance.getFoo().size(), equalTo(3));
		expression = parser.parseExpression("foo[3]");
		try {
			expression.setValue(ctx, "3");
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.UNABLE_TO_GROW_COLLECTION, see.getMessageCode());
			assertThat(instance.getFoo().size(), equalTo(3));
		}
	}
