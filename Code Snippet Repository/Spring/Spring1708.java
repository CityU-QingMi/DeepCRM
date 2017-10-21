	@Test
	public void testFileEditorWithRelativePath() {
		PropertyEditor fileEditor = new FileEditor();
		try {
			fileEditor.setAsText("myfile.txt");
		}
		catch (IllegalArgumentException ex) {
			// expected: should get resolved as class path resource,
			// and there is no such resource in the class path...
		}
	}
