	@Test
	public void testCallingIllegalFunctions() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("notStatic", this.getClass().getMethod("nonStatic"));
		try {
			@SuppressWarnings("unused")
			Object v = parser.parseRaw("#notStatic()").getValue(ctx);
			fail("Should have failed with exception - cannot call non static method that way");
		}
		catch (SpelEvaluationException se) {
			if (se.getMessageCode() != SpelMessage.FUNCTION_MUST_BE_STATIC) {
				se.printStackTrace();
				fail("Should have failed a message about the function needing to be static, not: "
						+ se.getMessageCode());
			}
		}
	}
