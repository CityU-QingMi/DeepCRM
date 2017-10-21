	@Test
	public void assertXmlEqualExceptionForIncorrectValue() throws Exception {
		final String control = "<root><field1>f1</field1><field2>f2</field2></root>";
		final String test = "<root><field1>notf1</field1><field2>f2</field2></root>";

		exception.expect(AssertionError.class);
		exception.expectMessage(Matchers.startsWith("Body content Expected child 'field1'"));

		final XmlExpectationsHelper xmlHelper = new XmlExpectationsHelper();
		xmlHelper.assertXmlEqual(control, test);
	}
