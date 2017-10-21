	@SuppressWarnings("")
	@Test
	public void resolveMapKeyValueTypes() {
		mapNotGeneric = new HashMap();
		mapNotGeneric.put("baseAmount", 3.11);
		mapNotGeneric.put("bonusAmount", 7.17);
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("mapNotGeneric");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.util.HashMap<?, ?>", expression.getValueTypeDescriptor(this).toString());
	}
