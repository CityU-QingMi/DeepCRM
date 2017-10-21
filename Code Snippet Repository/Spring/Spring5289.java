	@Test
	public void testSystemPropertyReplacement() {
		PropertyEditor editor = new ResourceEditor();
		System.setProperty("test.prop", "foo");
		try {
			editor.setAsText("${test.prop}-${bar}");
			Resource resolved = (Resource) editor.getValue();
			assertEquals("foo-${bar}", resolved.getFilename());
		}
		finally {
			System.getProperties().remove("test.prop");
		}
	}
