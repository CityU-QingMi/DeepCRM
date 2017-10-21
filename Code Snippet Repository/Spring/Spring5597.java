	@Test
	public void enableIndentingSunnyDay() throws Exception {
		Transformer transformer = new StubTransformer();
		TransformerUtils.enableIndenting(transformer);
		String indent = transformer.getOutputProperty(OutputKeys.INDENT);
		assertNotNull(indent);
		assertEquals("yes", indent);
		String indentAmount = transformer.getOutputProperty("{http://xml.apache.org/xslt}indent-amount");
		assertNotNull(indentAmount);
		assertEquals(String.valueOf(TransformerUtils.DEFAULT_INDENT_AMOUNT), indentAmount);
	}
