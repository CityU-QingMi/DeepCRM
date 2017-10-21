	@Test
	void fileWithPosition() throws Exception {
		File file = new File("test.txt");
		FilePosition position = FilePosition.from(42, 23);
		FileSource source = FileSource.from(file, position);

		assertThat(source.getUri()).isEqualTo(file.getAbsoluteFile().toURI());
		assertThat(source.getFile()).isEqualTo(file.getAbsoluteFile());
		assertThat(source.getPosition()).hasValue(position);
	}
