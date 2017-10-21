	private void assertIsCompiled(Expression expression) {
		try {
			Field field = SpelExpression.class.getDeclaredField("compiledAst");
			field.setAccessible(true);
			Object object = field.get(expression);
			assertNotNull(object);
		}
		catch (Exception ex) {
			fail(ex.toString());
		}
	}
