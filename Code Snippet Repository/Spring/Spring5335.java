	@Test(expected = IllegalArgumentException.class)
	public void testStrictSystemPropertyReplacement() {
		PropertyEditor editor = new ResourceArrayPropertyEditor(
				new PathMatchingResourcePatternResolver(), new StandardEnvironment(),
				false);
		System.setProperty("test.prop", "foo");
		try {
			editor.setAsText("${test.prop}-${bar}");
			Resource[] resources = (Resource[]) editor.getValue();
			assertEquals("foo-${bar}", resources[0].getFilename());
		}
		finally {
			System.getProperties().remove("test.prop");
		}
	}
