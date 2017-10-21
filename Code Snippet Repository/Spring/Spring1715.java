	@Test
	public void testCustomDateEditorWithExactDateLength() {
		int maxLength = 10;
		String validDate = "01/01/2005";
		String invalidDate = "01/01/05";

		assertTrue(validDate.length() == maxLength);
		assertFalse(invalidDate.length() == maxLength);

		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true, maxLength);

		try {
			editor.setAsText(validDate);
		}
		catch (IllegalArgumentException ex) {
			fail("Exception shouldn't be thrown because this is a valid date");
		}

		try {
			editor.setAsText(invalidDate);
			fail("Exception should be thrown because this is an invalid date");
		}
		catch (IllegalArgumentException ex) {
			// expected
			assertTrue(ex.getMessage().contains("10"));
		}
	}
