	@Test
	public void testGetValueType() throws Exception {
		LiteralExpression lEx = new LiteralExpression("somevalue");
		assertEquals(String.class, lEx.getValueType());
		assertEquals(String.class, lEx.getValueType(new StandardEvaluationContext()));
		assertEquals(String.class, lEx.getValueType(new Rooty()));
		assertEquals(String.class, lEx.getValueType(new StandardEvaluationContext(), new Rooty()));
		assertEquals(String.class, lEx.getValueTypeDescriptor().getType());
		assertEquals(String.class, lEx.getValueTypeDescriptor(new StandardEvaluationContext()).getType());
		assertEquals(String.class, lEx.getValueTypeDescriptor(new Rooty()).getType());
		assertEquals(String.class, lEx.getValueTypeDescriptor(new StandardEvaluationContext(), new Rooty()).getType());
	}
