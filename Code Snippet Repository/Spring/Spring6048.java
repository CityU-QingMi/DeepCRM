	@Test
	public void projectionTypeDescriptors_2() throws Exception {
		StandardEvaluationContext ctx = new StandardEvaluationContext(new C());
		SpelExpressionParser parser = new SpelExpressionParser();
		String el1 = "as.![#this.equals('abc')]";
		SpelExpression exp = parser.parseRaw(el1);
		Object[] value = (Object[]) exp.getValue(ctx);
		// value is array containing [true,false]
		assertEquals(Boolean.class, value[0].getClass());
		TypeDescriptor evaluated = exp.getValueTypeDescriptor(ctx);
		assertEquals(Boolean.class, evaluated.getElementTypeDescriptor().getType());
	}
