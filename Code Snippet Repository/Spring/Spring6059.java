	@Test
	public void customStaticFunctions_SPR9038() {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		List<MethodResolver> methodResolvers = new ArrayList<>();
		methodResolvers.add(new ReflectiveMethodResolver() {
			@Override
			protected Method[] getMethods(Class<?> type) {
				try {
					return new Method[] {
							Integer.class.getDeclaredMethod("parseInt", new Class<?>[] {String.class, Integer.TYPE})};
				}
				catch (NoSuchMethodException ex) {
					return new Method[0];
				}
			}
		});

		context.setMethodResolvers(methodResolvers);
		Expression expression = parser.parseExpression("parseInt('-FF', 16)");

		Integer result = expression.getValue(context, "", Integer.class);
		assertEquals(-255, result.intValue());
	}
