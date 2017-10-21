	@Test
	public void testScenario_UsingStandardInfrastructure() {
		try {
			// Create a parser
			SpelExpressionParser parser = new SpelExpressionParser();
			// Parse an expression
			Expression expr = parser.parseRaw("new String('hello world')");
			// Evaluate it using a 'standard' context
			Object value = expr.getValue();
			// They are reusable
			value = expr.getValue();

			assertEquals("hello world", value);
			assertEquals(String.class, value.getClass());
		}
		catch (EvaluationException ee) {
			ee.printStackTrace();
			fail("Unexpected Exception: " + ee.getMessage());
		}
		catch (ParseException pe) {
			pe.printStackTrace();
			fail("Unexpected Exception: " + pe.getMessage());
		}
	}
