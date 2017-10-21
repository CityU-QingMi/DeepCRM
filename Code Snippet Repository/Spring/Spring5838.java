	@Test
	public void operatorVariants() throws Exception {
		SpelExpression expr = (SpelExpression)parser.parseExpression("#a < #b");
		EvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("a", (short)3);
		ctx.setVariable("b", (short)6);
		assertTrue(expr.getValue(ctx, Boolean.class));
		ctx.setVariable("b", (byte)6);
		assertTrue(expr.getValue(ctx, Boolean.class));
		ctx.setVariable("a", (byte)9);
		ctx.setVariable("b", (byte)6);
		assertFalse(expr.getValue(ctx, Boolean.class));
		ctx.setVariable("a", 10L);
		ctx.setVariable("b", (short)30);
		assertTrue(expr.getValue(ctx, Boolean.class));
		ctx.setVariable("a", (byte)3);
		ctx.setVariable("b", (short)30);
		assertTrue(expr.getValue(ctx, Boolean.class));
		ctx.setVariable("a", (byte)3);
		ctx.setVariable("b", 30L);
		assertTrue(expr.getValue(ctx, Boolean.class));
		ctx.setVariable("a", (byte)3);
		ctx.setVariable("b", 30f);
		assertTrue(expr.getValue(ctx, Boolean.class));
		ctx.setVariable("a", new BigInteger("10"));
		ctx.setVariable("b", new BigInteger("20"));
		assertTrue(expr.getValue(ctx, Boolean.class));
	}
