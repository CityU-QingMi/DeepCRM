	@Test
	public void projectionTypeDescriptors_1() throws Exception {
		StandardEvaluationContext ctx = new StandardEvaluationContext(new C());
		SpelExpressionParser parser = new SpelExpressionParser();
		String el1 = "ls.![#this.equals('abc')]";
		SpelExpression exp = parser.parseRaw(el1);
		List<?> value = (List<?>) exp.getValue(ctx);
		// value is list containing [true,false]
		assertEquals(Boolean.class, value.get(0).getClass());
		TypeDescriptor evaluated = exp.getValueTypeDescriptor(ctx);
		assertEquals(null, evaluated.getElementTypeDescriptor());
	}
