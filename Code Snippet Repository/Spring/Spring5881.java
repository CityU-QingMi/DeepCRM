	@Test
	public void testGetValue() throws Exception {
		LiteralExpression lEx = new LiteralExpression("somevalue");
		checkString("somevalue", lEx.getValue());
		checkString("somevalue", lEx.getValue(String.class));
		EvaluationContext ctx = new StandardEvaluationContext();
		checkString("somevalue", lEx.getValue(ctx));
		checkString("somevalue", lEx.getValue(ctx, String.class));
		checkString("somevalue", lEx.getValue(new Rooty()));
		checkString("somevalue", lEx.getValue(new Rooty(), String.class));
		checkString("somevalue", lEx.getValue(ctx, new Rooty()));
		checkString("somevalue", lEx.getValue(ctx, new Rooty(),String.class));
		assertEquals("somevalue", lEx.getExpressionString());
		assertFalse(lEx.isWritable(new StandardEvaluationContext()));
		assertFalse(lEx.isWritable(new Rooty()));
		assertFalse(lEx.isWritable(new StandardEvaluationContext(), new Rooty()));
	}
