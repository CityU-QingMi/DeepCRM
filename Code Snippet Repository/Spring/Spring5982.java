	@Test
	public void propertyReferenceVisibility() { // SPR-12771
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("httpServletRequest", HttpServlet3RequestFactory.getOne());
		// Without a fix compilation was inserting a checkcast to a private type
		expression = parser.parseExpression("#httpServletRequest.servletPath");
		assertEquals("wibble", expression.getValue(ctx));
		assertCanCompile(expression);
		assertEquals("wibble", expression.getValue(ctx));
	}
