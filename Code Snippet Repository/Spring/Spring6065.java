	@Test
	public void SPR9495() throws Exception {
		SpelParserConfiguration configuration = new SpelParserConfiguration(false, false);
		ExpressionParser parser = new SpelExpressionParser(configuration);

		StandardEvaluationContext context = new StandardEvaluationContext();
		Expression spel = parser.parseExpression("#enumType.values()");

		context.setVariable("enumType", ABC.class);
		Object result = spel.getValue(context);
		assertNotNull(result);
		assertTrue(result.getClass().isArray());
		assertEquals(ABC.A, Array.get(result, 0));
		assertEquals(ABC.B, Array.get(result, 1));
		assertEquals(ABC.C, Array.get(result, 2));

		context.addMethodResolver(new MethodResolver() {
			@Override
			public MethodExecutor resolve(EvaluationContext context, Object targetObject, String name,
					List<TypeDescriptor> argumentTypes) throws AccessException {
				return new MethodExecutor() {
					@Override
					public TypedValue execute(EvaluationContext context, Object target, Object... arguments)
							throws AccessException {
						try {
							Method method = XYZ.class.getMethod("values");
							Object value = method.invoke(target, arguments);
							return new TypedValue(value, new TypeDescriptor(new MethodParameter(method, -1)).narrow(value));
						}
						catch (Exception ex) {
							throw new AccessException(ex.getMessage(), ex);
						}
					}
				};
			}
		});

		result = spel.getValue(context);
		assertNotNull(result);
		assertTrue(result.getClass().isArray());
		assertEquals(XYZ.X, Array.get(result, 0));
		assertEquals(XYZ.Y, Array.get(result, 1));
		assertEquals(XYZ.Z, Array.get(result, 2));
	}
