	@Test
	public void testSetAsTextWithBaseNameAndLanguageCode() throws Exception {
		ResourceBundleEditor editor = new ResourceBundleEditor();
		editor.setAsText(BASE_NAME + "Lang" + "_en");
		Object value = editor.getValue();
		assertNotNull("Returned ResourceBundle was null (must not be for valid setAsText(..) call).", value);
		assertTrue("Returned object was not a ResourceBundle (must be for valid setAsText(..) call).",
				value instanceof ResourceBundle);
		ResourceBundle bundle = (ResourceBundle) value;
		String string = bundle.getString(MESSAGE_KEY);
		assertEquals("yob", string);
	}
