	@Test
	void equalsAndHashCodeForFileSource() {
		File file1 = new File("foo.txt");
		File file2 = new File("bar.txt");
		assertEqualsAndHashCode(FileSource.from(file1), FileSource.from(file1), FileSource.from(file2));

		FilePosition position = FilePosition.from(42, 23);
		assertEqualsAndHashCode(FileSource.from(file1, position), FileSource.from(file1, position),
			FileSource.from(file2, position));
	}
