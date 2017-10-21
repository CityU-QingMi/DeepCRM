	@SuppressWarnings("")
	@Test
	public void resolveCollectionElementType() {
		listNotGeneric = new ArrayList(2);
		listNotGeneric.add(5);
		listNotGeneric.add(6);
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("listNotGeneric");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.util.ArrayList<?>", expression.getValueTypeDescriptor(this).toString());
		assertEquals("5,6", expression.getValue(this, String.class));
	}
