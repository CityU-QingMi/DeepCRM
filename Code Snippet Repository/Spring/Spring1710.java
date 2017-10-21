	@Test
	public void testFileEditorWithAbsolutePath() {
		PropertyEditor fileEditor = new FileEditor();
		// testing on Windows
		if (new File("C:/myfile.txt").isAbsolute()) {
			fileEditor.setAsText("C:/myfile.txt");
			assertEquals(new File("C:/myfile.txt"), fileEditor.getValue());
		}
		// testing on Unix
		if (new File("/myfile.txt").isAbsolute()) {
			fileEditor.setAsText("/myfile.txt");
			assertEquals(new File("/myfile.txt"), fileEditor.getValue());
		}
	}
