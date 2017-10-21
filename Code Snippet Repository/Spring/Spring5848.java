	@Test
	public void testTypeLocator() throws EvaluationException {
		ExpressionState state = getState();
		assertNotNull(state.getEvaluationContext().getTypeLocator());
		assertEquals(Integer.class,state.findType("java.lang.Integer"));
		try {
			state.findType("someMadeUpName");
			fail("Should have failed to find it");
		}
		catch (EvaluationException ee) {
			SpelEvaluationException sEx = (SpelEvaluationException)ee;
			assertEquals(SpelMessage.TYPE_NOT_FOUND,sEx.getMessageCode());
		}
	}
