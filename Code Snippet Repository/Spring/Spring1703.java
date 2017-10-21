	@Test
	public void testParseShortGreaterThanMaxValueWithoutNumberFormat() {
		try {
			CustomNumberEditor editor = new CustomNumberEditor(Short.class, true);
			editor.setAsText(String.valueOf(Short.MAX_VALUE + 1));
			fail(Short.MAX_VALUE + 1 + " is greater than max value");
		}
		catch (NumberFormatException ex) {
			// expected
		}
	}
