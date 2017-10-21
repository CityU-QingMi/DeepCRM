	@Test
	public void SPR10125() throws Exception {
		StandardEvaluationContext context = new StandardEvaluationContext();
		String fromInterface = parser.parseExpression("T(" + StaticFinalImpl1.class.getName() + ").VALUE").getValue(
				context, String.class);
		assertThat(fromInterface, is("interfaceValue"));
		String fromClass = parser.parseExpression("T(" + StaticFinalImpl2.class.getName() + ").VALUE").getValue(
				context, String.class);
		assertThat(fromClass, is("interfaceValue"));
	}
