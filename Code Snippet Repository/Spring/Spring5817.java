	@Test
	public void testResolvingList() throws Exception {
		StandardEvaluationContext context = TestScenarioCreator.getTestEvaluationContext();
		try {
			assertFalse(parser.parseExpression("T(List)!=null").getValue(context, Boolean.class));
			fail("should have failed to find List");
		}
		catch (EvaluationException ee) {
			// success - List not found
		}
		((StandardTypeLocator) context.getTypeLocator()).registerImport("java.util");
		assertTrue(parser.parseExpression("T(List)!=null").getValue(context, Boolean.class));
	}
