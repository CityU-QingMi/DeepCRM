	@Test
	public void collectionGrowingViaIndexer() {
		Spr9751 instance = new Spr9751();

		// Add a new element to the list
		StandardEvaluationContext ctx = new StandardEvaluationContext(instance);
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Expression e =  parser.parseExpression("listOfStrings[++index3]='def'");
		e.getValue(ctx);
		assertEquals(2,instance.listOfStrings.size());
		assertEquals("def",instance.listOfStrings.get(1));

		// Check reference beyond end of collection
		ctx = new StandardEvaluationContext(instance);
		parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		e =  parser.parseExpression("listOfStrings[0]");
		String value = e.getValue(ctx,String.class);
		assertEquals("abc",value);
		e =  parser.parseExpression("listOfStrings[1]");
		value = e.getValue(ctx,String.class);
		assertEquals("def",value);
		e =  parser.parseExpression("listOfStrings[2]");
		value = e.getValue(ctx,String.class);
		assertEquals("",value);

		// Now turn off growing and reference off the end
		ctx = new StandardEvaluationContext(instance);
		parser = new SpelExpressionParser(new SpelParserConfiguration(false, false));
		e =  parser.parseExpression("listOfStrings[3]");
		try {
			e.getValue(ctx,String.class);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.COLLECTION_INDEX_OUT_OF_BOUNDS,see.getMessageCode());
		}
	}
