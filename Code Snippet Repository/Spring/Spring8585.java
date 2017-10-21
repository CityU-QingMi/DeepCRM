	@Test
	public void assertXmlEqualExceptionForMoreEntries() throws Exception {
		final String control = "<root><field1>f1</field1><field2>f2</field2></root>";
		final String test = "<root><field1>f1</field1><field2>f2</field2><field3>f3</field3></root>";

		exception.expect(AssertionError.class);
		exception.expectMessage(Matchers.containsString("Expected child nodelist length '2' but was '3'"));

		final XmlExpectationsHelper xmlHelper = new XmlExpectationsHelper();
		xmlHelper.assertXmlEqual(control, test);
	}
