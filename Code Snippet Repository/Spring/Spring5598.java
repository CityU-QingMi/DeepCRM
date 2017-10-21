	@Test
	public void enableIndentingSunnyDayWithCustomKosherIndentAmount() throws Exception {
		final String indentAmountProperty = "10";
		Transformer transformer = new StubTransformer();
		TransformerUtils.enableIndenting(transformer, Integer.valueOf(indentAmountProperty));
		String indent = transformer.getOutputProperty(OutputKeys.INDENT);
		assertNotNull(indent);
		assertEquals("yes", indent);
		String indentAmount = transformer.getOutputProperty("{http://xml.apache.org/xslt}indent-amount");
		assertNotNull(indentAmount);
		assertEquals(indentAmountProperty, indentAmount);
	}
